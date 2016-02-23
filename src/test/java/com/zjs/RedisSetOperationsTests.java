package com.zjs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * SetOperations基本使用的测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RedisDemoApplication.class)
public class RedisSetOperationsTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void contextLoads() {
    }

    /**
     * set get
     */
    @Test
    public void testSetGet() {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

        String key = "redis_set";

        // 设置set中的元素
        setOperations.add(key, "a", "b", "c", "d");
        setOperations.add(key, "a", "e", "b");

        // 获取set中的元素
        Set<String> member = setOperations.members(key);

        System.out.println("members is :");
        for (String s : member) {
            System.out.println(s);
        }

        // 判断是否是set中的元素
        boolean aIsMember = setOperations.isMember(key, "a");
        boolean fIsMember = setOperations.isMember(key, "f");

        System.out.println("a is member : " + aIsMember);
        System.out.println("f is member : " + fIsMember);

        // 查询set的size
        long size = setOperations.size(key);
        System.out.println("set's size is : " + size);

        // 随机返回一个元素
        String randomMember = setOperations.randomMember(key);
        System.out.println(randomMember);

        List<String> randomMemberList = setOperations.randomMembers(key, 3);
        System.out.println("random member list is : ");
        for (String s : randomMemberList) {
            System.out.println(s);
        }

    }


}
