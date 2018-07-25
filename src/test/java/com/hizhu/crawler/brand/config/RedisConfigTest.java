package com.hizhu.crawler.brand.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/24 14:01
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring.xml")
public class RedisConfigTest {

    @Autowired
    RedisConnectionFactory connectionFactory;
//    @Autowired(required = false)
//    RedisTemplate redisTemplate;

    @Test
    public void test(){
        final RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();

        /**
         * RedisClusterConnection.clusterGetNodes()方法构造 RedisClusterNode
         */
        final Iterable<RedisClusterNode> nodes = clusterConnection.clusterGetNodes();
        /**
         * 构造函数直接构造 RedisClusterNode
         */
        RedisClusterNode redisClusterNode = new RedisClusterNode(null,7001);
        final Set<byte[]> keys = clusterConnection.keys(redisClusterNode, "*".getBytes());
        System.out.println(keys.size());

        final byte[] bytes = clusterConnection.get("java_test".getBytes());
        System.out.println(new String(bytes));

        clusterConnection.mGet("java_test".getBytes(),"".getBytes());

//        ClusterOperations clusterOps = redisTemplate.opsForCluster();
//        clusterOps.shutdown(redisClusterNode);

    }
}
