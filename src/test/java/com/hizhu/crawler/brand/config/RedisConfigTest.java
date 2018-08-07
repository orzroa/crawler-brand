package com.hizhu.crawler.brand.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
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
        RedisClusterNode redisClusterNode = new RedisClusterNode("121.41.36.30",7000);
        final Set<byte[]> keys47000 = clusterConnection.keys(redisClusterNode, "d3*".getBytes());
        System.out.println("7000 端口的d3的key的数量: " + keys47000.size());

        final Set<byte[]> keys4All = clusterConnection.keys("d3*".getBytes());
        System.out.println("所有端口的d3的key的数量: " + keys4All.size());

        final Set<byte[]> keys = clusterConnection.keys("jav*".getBytes());
        keys.forEach(key->{
            System.out.println(new String(key));
        });

        final byte[] bytes = clusterConnection.get("java_test".getBytes());
        System.out.println(new String(bytes));
        final List<byte[]> lists = clusterConnection.mGet("java_test".getBytes(), "jav_kk".getBytes(), "java_set".getBytes());

        lists.forEach(list->{
            System.out.println(new String(list));
        });

//        ClusterOperations clusterOps = redisTemplate.opsForCluster();
//        clusterOps.shutdown(redisClusterNode);

    }
}
