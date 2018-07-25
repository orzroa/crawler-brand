package com.hizhu.crawler.brand.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description ：Redis cluster properties
 * @author： manji
 * 2018/7/23 14:09
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisClusterProperties {

    /**
     * @Configuration__Properties
     *      spring.redis.cluster.nodes: Comma delimited list of host:port pairs. 逗号分隔的host:port对
     *          spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     *          spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     *      spring.redis.cluster.max-redirects: Number of allowed cluster redirections. 允许集群重定向的数量
     */
    List<String> nodes;
}
