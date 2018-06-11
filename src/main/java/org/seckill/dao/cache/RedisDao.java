package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePool;
import org.springframework.stereotype.Service;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class RedisDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RuntimeSchema<Seckill> runtimeSchema = RuntimeSchema.createFrom(Seckill.class);

    @Resource
    private LettuceConnectionFactory connectionFactory;

    private static final String REDIS_KEY_PREFIX = "seckill:";


    public Seckill getSeckill(long seckillId){
        try{
            RedisConnection connection = connectionFactory.getConnection();
            String key = getRedisKey(String.valueOf(seckillId));
            return Optional.ofNullable(connection.get(key.getBytes()))
                    .map(bytes -> {
                        Seckill seckill = runtimeSchema.newMessage();
                        ProtostuffIOUtil.mergeFrom(bytes,seckill,runtimeSchema);
                        return seckill;
                    }).orElse(null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }


    public boolean putSeckill(Seckill seckill){
        try{
            RedisConnection connection = connectionFactory.getConnection();

            String key = getRedisKey(String.valueOf(seckill.getId()));
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,runtimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            int timeout = 3600;
            return connection.setEx(key.getBytes(),timeout,bytes);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return false;
    }

    private String getRedisKey(String seckillId){
        return REDIS_KEY_PREFIX + seckillId;
    }

}
