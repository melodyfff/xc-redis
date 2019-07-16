package com.xinchen.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 *
 * 使用redisson作为连接
 *
 * 官方例子： https://github.com/redisson/redisson-examples
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/7/16 22:57
 */
@Configuration
@Profile("redisson")
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson/redisson-single.yaml").getInputStream()));

    }

}
