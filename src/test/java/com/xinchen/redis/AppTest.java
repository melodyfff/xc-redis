package com.xinchen.redis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @author xinchen
 * @version 1.0
 * @date 04/12/2018 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void initContext(){}

    @Test
    public void testString(){
        // 1. 设置redis的序列化方式
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
        redisTemplate.opsForValue().set("ok","ok");
        LOG.info(redisTemplate.opsForValue().get("ok").toString());

        final ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("test", "test");
        LOG.info(valueOperations.get("test"));


        // 2. 直接使用StringRedisTemplate存取
        stringRedisTemplate.opsForValue().set("test1","test1");
        LOG.info(stringRedisTemplate.opsForValue().get("test1"));
    }


    @Test
    public void testList(){
        List<String> list = Arrays.asList("test1", "test2", "test3");
        redisTemplate.opsForList().leftPush("list",list);
        LOG.info(redisTemplate.opsForList().size("list").toString());

        stringRedisTemplate.opsForValue().set("list", JSONObject.toJSONString(list));
        System.out.println(stringRedisTemplate.opsForValue().get("list"));
        final List<String> list2 = JSONObject.parseArray(stringRedisTemplate.opsForValue().get("list"), String.class);
        System.out.println(list2);


        final List list1 = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println(list1);

    }

}