package org.seckill.service;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SuccesskilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.exception.RepeatKillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeckillDao seckillDao;

    @Resource
    private SuccesskilledDao successkilledDao;

    @Resource
    private RedisDao redisDao;

    private final String slat = "ahkacfku347tq73trkca7tktxe7fxf^%(*&^(*^IU%FU%EYWXST#$JHGKJGJfjytrj";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null){
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null){
                return new Exposer(false,seckillId);
            }else {
                redisDao.putSeckill(seckill);
            }
        }


        if (seckill == null){
            return new Exposer(false,seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }

        String md5= getMd5(seckillId);

        return new Exposer(true,md5,seckillId);

    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {

        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        try {

            //记录购买行为
            int insertCount = successkilledDao.insertSuccessKilled(seckillId, (short)1,userPhone);
            //唯一id，userphone
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeat");
            } else {
                //执行秒杀逻辑
                int updateCount = seckillDao.reduceNumber(seckillId, new Date());
                //没有更新到记录
                if (updateCount <= 0) {
                    throw new SeckillCloseException("seckill closed");
                } else {
                    SuccessKilled successKilled = successkilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }


        }catch (SeckillCloseException e1){
            throw e1;
        } catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill inner error");
        }


    }

    @Override
    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {

        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            return new SeckillExecution(seckillId,SeckillStatEnum.DATA_REWRITE);
        }

        Date killTime = new Date();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("seckillId",seckillId);
        map.put("phone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);

        try{
            seckillDao.killByProcedure(map);
            //获取result
            int result = MapUtils.getInteger(map,"result",-2);
            if (result == 1){
                SuccessKilled successKilled = successkilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS,successKilled);
            }else {
                return new SeckillExecution(seckillId,SeckillStatEnum.stateOf(result));
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,SeckillStatEnum.INNER_ERROR);
        }

    }

    private String getMd5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;

    }
}
