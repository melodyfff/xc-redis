import com.xinchen.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xinchen on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:redis-content.xml"})
public class test {
    @Autowired
    private RedisService redisService;
    @Test
    public void testSpringRedis() {
//        s.add("1");
//        s.add("2");
//        s.add("2");
//        System.out.println(redisService.leftList("testList",s));
//        System.out.println(redisService.leftList("testList",s));
//        System.out.println(redisService.leftList("testList"));
//        System.out.println(redisService.listSize("testList"));
        List<String> linkedHashSetList = new LinkedList<>(redisService.getAllKeys());
        System.out.println(linkedHashSetList);
        System.out.println((String)redisService.get(linkedHashSetList.get(3)));
//        System.out.println(redisService.rightList("testList"));
    }
}
