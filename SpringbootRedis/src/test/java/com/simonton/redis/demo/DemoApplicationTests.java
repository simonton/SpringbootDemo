package com.simonton.redis.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedisHello() {
		String value = redisTemplate.boundValueOps("hello").get();
		System.out.println("hello : " + value);
	}

	/**
	 * redis list 使用场景：
	 *  1）数据有序
	 *  2）集合 对标java.util.List数据结构
	 */
	@Test
	public void testList() {

		BoundListOperations<String, String> boundListOperations = redisTemplate.boundListOps("listKey");
		boundListOperations.rightPush("hello");

		long len = boundListOperations.size();
		List<String> listData = boundListOperations.range(0, len);
		System.out.println("list data: " + JSON.toJSONString(listData));


		String redisData = boundListOperations.leftPop();

		Assert.assertEquals("hello", redisData);
		System.out.print(redisData);

	}

	/**
	 * redis set 使用场景：
	 *  1）数据无序且不重复
	 *  2）对于数据取交集，并集 和 差集有优势
	 *  3）对标java.util.Set 但底层不是map存储结构
	 */
	@Test
	public void testSet() {
		BoundSetOperations<String, String> boundSetOperations = redisTemplate.boundSetOps("setKey");
		boundSetOperations.add("a");
		boundSetOperations.add("s");
		boundSetOperations.add("h");

		Set<String> redisData = boundSetOperations.members();
		System.out.println(JSON.toJSONString(redisData));

		Assert.assertNotNull(redisData);

	}


	/**
	 * hash 使用场景和java.util.Map类似，redis值为key-value集合
	 */
	@Test
	public void testHash() {
		BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("hashKey");

		boundHashOperations.put("key1","value1");
		boundHashOperations.put("key2","value2");

		Map<String, String> redisData = boundHashOperations.entries();

		System.out.println(JSON.toJSONString(redisData));
	}

	@Test
	public void testString() {
		BoundValueOperations boundValueOperations = stringRedisTemplate.boundValueOps("stringKey");
		boundValueOperations.set("hello redis.");
		System.out.println(boundValueOperations.get());
		Assert.assertNotNull(boundValueOperations.get());
	}

}
