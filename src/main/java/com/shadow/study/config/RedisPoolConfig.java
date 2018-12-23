package com.shadow.study.config;

import com.shadow.study.properties.JedisProperties;
import com.shadow.study.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnClass(RedisClient.class)
public class RedisPoolConfig {

    @Autowired
    private JedisProperties jedisProperties;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = getJedisPoolConfig();
        JedisPool jedisPool = new JedisPool(config, jedisProperties.getHost(), jedisProperties.getPort(),
                jedisProperties.getMaxWaitMillis(), jedisProperties.getPassword());
        return jedisPool;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
//        System.out.println("初始化……Redis Client==Host={},Port={}:"  + jedisProperties.getHost() + ":" + jedisProperties.getPort());
        RedisClient redisClient = new RedisClient();
        redisClient.setJedisPool(pool);
        return redisClient;
    }

}

//修改陈慧