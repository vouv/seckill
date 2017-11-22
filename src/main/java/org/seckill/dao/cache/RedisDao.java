package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

@Service
public class RedisDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RuntimeSchema<Seckill> runtimeSchema = RuntimeSchema.createFrom(Seckill.class);

    private final JedisPool jedisPool;


    public RedisDao() {
        this.jedisPool = new JedisPool();
    }


    public Seckill getSeckill(long seckillId){
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:" + seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes !=null){
                    Seckill seckill = runtimeSchema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,runtimeSchema);
                    return seckill;
                }

            }finally {
                jedis.close();
            }



        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill){
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:" + seckill.getId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,runtimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 3600;
                String res = jedis.setex(key.getBytes(),timeout,bytes);
                return res;

            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}
