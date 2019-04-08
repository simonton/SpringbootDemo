package com.simonton.redis.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void testRedisHello() {
		String value = redisTemplate.boundValueOps("hello").get();
		System.out.println("hello : " + value);
	}

}
