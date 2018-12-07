package com.xinchen.redis.core.down;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2018/12/7 23:36
 */
@Component
@Slf4j
public class ImageHelper {

    private final RestTemplate restTemplate;


    private final ByteRedisTemplate byteRedisTemplate;

    @Autowired
    public ImageHelper(RestTemplate restTemplate, ByteRedisTemplate byteRedisTemplate) {
        this.restTemplate = restTemplate;
        this.byteRedisTemplate = byteRedisTemplate;
    }

    public void down(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Referer","http://www.mmjpg.com");
        headers.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");

        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange("http://fm.shiyunjj.com/2018/1542/4i6p.jpg",
                HttpMethod.GET, httpEntity, byte[].class);
        log.info("------------");
        log.info(">> StatusCode  : {}",responseEntity.getStatusCode());
        log.info(">> ContentType : {}",responseEntity.getHeaders().getContentType());
        log.info(">> Subtype     : {}",responseEntity.getHeaders().getContentType().getSubtype());
        log.info("------------");

        try {
            File dir = new File("");
            File file = File.createTempFile("hello", "." + responseEntity.getHeaders().getContentType().getSubtype(),dir);
            FileOutputStream os = new FileOutputStream(file);
            final byte[] body = responseEntity.getBody();
            // 存储到redis
            byteRedisTemplate.opsForValue().set("img",body);
            os.write(body);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
