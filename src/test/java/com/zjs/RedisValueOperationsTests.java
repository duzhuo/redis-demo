package com.zjs;

import com.zjs.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * ValueOperations基本使用的测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RedisDemoApplication.class)
public class RedisValueOperationsTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
//    RedisTemplate<String,User> redisTemplate;



    @Test
    public void contextLoads() {
    }

    /**
     * set get
     */
    @Test
    public void testSetGet() {
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
        String key = "string_redis_template";
        String v = "use StringRedisTemplate set k v";
        vop.set(key, v);
        System.out.println("");
        System.out.println("redis value is " + vop.get(key));
    }

    /**
     * 多值赋值 取值
     */
    @Test
    public void testMultiSetGet() {
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();

        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");

        vop.multiSet(map);

        Set<String> keySet = new HashSet<>();
        keySet.add("k1");
        keySet.add("k2");
        keySet.add("k3");

        List<String> list = vop.multiGet(keySet);

        System.out.println("从Redis取得如下信息");

        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 增加 或 减少
     */
    @Test
    public void testIncrement() {
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();

        for (int i = 0; i < 1000; i++) {
            vop.increment("incre1", 2);
        }

        System.out.println("incre1 value is ");
        System.out.println(vop.get("incre1"));

        for (int i = 0; i < 1000; i++) {
            vop.increment("incre2", -0.5);
        }
        System.out.println("incre2 value is ");
        System.out.println(vop.get("incre2"));

    }

    /**
     * 设置 取值 DTO
     * 注意事项
     * 1.RedisTemplate注入是不设置泛型
     * 2.DTO需实现序列化,否则无法保存
     */
    @Test
    public void testSetGetDto() {
        ValueOperations<String,User> vop = redisTemplate.opsForValue();

        User user = new User();
        user.setUserCode("001");
        user.setUserName("Mike");

        vop.set("001", user);

        User getUser = vop.get("001");
        System.out.println("get User value is");
        System.out.println(getUser.toString());

    }
}
