package com.xinchen.redis;

import com.alibaba.fastjson.JSONObject;
import com.xinchen.redis.core.down.ByteRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2018/12/8 0:34
 */
@Slf4j
public class DemoTest extends AppTest{

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ByteRedisTemplate byteRedisTemplate;

    @Test
    public void initContext(){}

    @Test
    public void testString(){
        // 1. 设置redis的序列化方式
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
        redisTemplate.opsForValue().set("ok","ok");
        log.info(redisTemplate.opsForValue().get("ok").toString());

        final ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("test", "test");
        log.info(valueOperations.get("test"));


        // 2. 直接使用StringRedisTemplate存取
        stringRedisTemplate.opsForValue().set("test1","test1");
        log.info(stringRedisTemplate.opsForValue().get("test1"));
    }


    @Test
    public void testList(){
        List<String> list = Arrays.asList("test1", "test2", "test3");
        redisTemplate.opsForList().leftPush("list",list);
        log.info(redisTemplate.opsForList().size("list").toString());

        stringRedisTemplate.opsForValue().set("list", JSONObject.toJSONString(list));
        System.out.println(stringRedisTemplate.opsForValue().get("list"));
        final List<String> list2 = JSONObject.parseArray(stringRedisTemplate.opsForValue().get("list"), String.class);
        System.out.println(list2);


        final List list1 = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println(list1);

    }


    @Test
    public void testMap()  {

        List test = new ArrayList();
        test.add("test1");

        Map map = new HashMap<>();
        map.put("ok", "ok");
        map.put("test1", test);
        stringRedisTemplate.opsForValue().set("ok",JSONObject.toJSONString(map));

        System.out.println(stringRedisTemplate.opsForValue().get("ok"));


    }

    @Test
//    @Ignore
    public void testByte() throws IOException {
        File file = new File("123.jpeg");
        FileOutputStream os = new FileOutputStream(file);
        os.write(byteRedisTemplate.opsForValue().get("img"));
        os.flush();
        os.close();
    }
}
