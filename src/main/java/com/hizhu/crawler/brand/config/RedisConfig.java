package com.hizhu.crawler.brand.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.lang.Nullable;

import static org.springframework.util.Assert.*;


/**
 * Description ：Redis 集群客户端
 * @author： manji
 * 2018/7/23 14:10
 */
@Configuration
public class RedisConfig {

    @Autowired
    RedisClusterProperties redisClusterProperties;

    @Value(value = "${redis.password:}")
    private @Nullable String password;

    /**
     * RedisClusterConfiguration can also be defined via PropertySource.
     * @return
     */
    @Bean
    public  RedisConnectionFactory connectionFactory(){
        RedisClusterConfiguration clusterConf = new RedisClusterConfiguration(redisClusterProperties.getNodes());
        clusterConf.setPassword(RedisPassword.of(password));
        return  new JedisConnectionFactory(clusterConf);
    }
}
