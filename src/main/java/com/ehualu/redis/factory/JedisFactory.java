package com.ehualu.redis.factory;

import com.ehualu.redis.JedisInterface;
import com.ehualu.redis.hash.HashRedis;
import com.ehualu.redis.sharded.ShardedRedis;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * Created by Administrator on 2017/9/27.
 */
public class JedisFactory implements FactoryBean<JedisInterface>{

    @Value("${redis.pool.type}")
    private String redisPoolType;
    @Value("${redis.timeout}")
    private String timeout;
    @Value("${redis.jedisPoolConfig.maxTotal}")
    private String maxTotal;
    @Value("${redis.jedisPoolConfig.maxIdle}")
    private String maxIdle;
    @Value("${redis.jedisPoolConfig.minIdle}")
    private String minIdle;
    @Value("${redis.jedisPoolConfig.maxWaitTime}")
    private String maxWaitTime;
    @Value("${redis.jedisPoolConfig.testOnBorrow}")
    private String testOnBorrow;
    @Value("${redis.jedisPoolConfig.urls}")
    private String redisUrls;

    @Override
    public JedisInterface getObject() throws Exception {
        Properties properties =  new Properties();
        properties.setProperty("redis.timeout",timeout);
        properties.setProperty("redis.jedisPoolConfig.maxTotal",maxTotal);
        properties.setProperty("redis.jedisPoolConfig.maxIdle",maxIdle);
        properties.setProperty("redis.jedisPoolConfig.minIdle",minIdle);
        properties.setProperty("redis.jedisPoolConfig.maxWaitTime",maxWaitTime);
        properties.setProperty("redis.jedisPoolConfig.testOnBorrow",testOnBorrow);
        properties.setProperty("redis.jedisPoolConfig.urls",redisUrls);
        JedisInterface jedisInterface = null;
        if("hash".equals(redisPoolType)){
            jedisInterface = new HashRedis(properties);
        }else{
            jedisInterface = new ShardedRedis(properties);
        }
        return jedisInterface;
    }

    @Override
    public Class<? extends JedisInterface> getObjectType() {
        if("hash".equals(redisPoolType)){
            return HashRedis.class;
        }else{
            return ShardedRedis.class;
        }
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
