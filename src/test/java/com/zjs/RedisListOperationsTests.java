package com.zjs;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ListOperations基本使用的测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RedisDemoApplication.class)
public class RedisListOperationsTests {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllList() {
        String key = "spring";
        ListOperations<String, String> lop = redisTemplate.opsForList();
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);

        // 删除list
        redisTemplate.delete(key);

        // 插入list
        for (int i = 0; i < 10; i++) {
            lop.leftPush(key, "" + i);
        }

        // 取整个list
        List<String> list = lop.range(key, 0, -1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        long size = lop.size(key);
        System.out.println("size: " + size);
    }

    @Test
    public void testRightPush() {
        String key = "right.push";
        ListOperations<String, String> lop = redisTemplate.opsForList();
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);

        // 删除list
        redisTemplate.delete(key);

        // 插入list
        for (int i = 0; i < 10; i++) {
            lop.rightPush(key, "" + i);
        }

        // 取2~8个元素
        List<String> list = lop.range(key, 2, 8);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        long allListSize = lop.size(key);
        System.out.println("all list size: " + allListSize);

        long readListSize = list.size();
        System.out.println("all list size: " + readListSize);

    }

    @Test
    public void testPop() {
        String key = "right.pop";
        ListOperations<String, String> lop = redisTemplate.opsForList();
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);

        // 删除list
        redisTemplate.delete(key);

        // 插入list
        for (int i = 0; i < 10; i++) {
            System.out.println("left push : " + i);
            lop.leftPush(key, "" + i);
        }

        long allListSize = lop.size(key);
        System.out.println("all list size: " + allListSize);

        // left pop
        for (int i = 0; i < allListSize; i++) {
            String str = lop.leftPop(key);
            System.out.println("left pop : " + str);
        }

        // 插入list
        for (int i = 0; i < 10; i++) {
            System.out.println("left push : " + i);
            lop.leftPush(key, "" + i);
        }

        // right pop
        for (int i = 0; i < allListSize; i++) {
            String str = lop.rightPop(key);
            System.out.println("right pop : " + str);
        }

    }

}
