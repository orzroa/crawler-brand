package com.hizhu.crawler.brand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author manji
 */
@SpringBootApplication
@EnableScheduling
@ImportResource("classpath*:spring/spring.xml")
public class CrawlerBrandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlerBrandApplication.class, args);
	}
}
