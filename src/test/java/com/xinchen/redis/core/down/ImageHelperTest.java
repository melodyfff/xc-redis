package com.xinchen.redis.core.down;

import com.xinchen.redis.AppTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2018/12/8 0:33
 */
public class ImageHelperTest extends AppTest {

    @Resource
    private ImageHelper imageHelper;

    @Test
    public void down() {
        imageHelper.down();
    }
}