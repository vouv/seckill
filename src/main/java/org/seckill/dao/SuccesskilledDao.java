package org.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;


@Mapper
public interface SuccesskilledDao {
    /**
     * 插入购买明细
     * 可以过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     * 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("state") short state, @Param("userPhone")long userPhone);


    /**
     * 根据id查询successKilled 并携带产品对象
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);







}
