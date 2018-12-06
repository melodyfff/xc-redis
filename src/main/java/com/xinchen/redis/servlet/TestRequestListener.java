package com.xinchen.redis.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Request:
 * ServletRequestListener : 监听Request的创建和销毁
 * ServletRequestAttributeListener : 监听Request的属性,新增属性、移除属性和属性值被替换时
 *
 * Session:
 * HttpSessionListener : 监听HttpSession的创建跟销毁
 * HttpSessionAttributeListener : 监听session中属性,新增属性、移除属性和属性值被替换时
 *
 * ServletContext:
 * ServletContextListener : 监听ServletContext的创建和销毁
 * ServletContextAttributeListener : 监听ServletContext中属性的新增、移除和属性值的替换
 *
 * @author xinchen
 * @version 1.0
 * @date 06/12/2018 10:33
 */
@WebListener
public class TestRequestListener implements ServletRequestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        LOGGER.info(">>> ServletRequestListener : request destroy.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        LOGGER.info(">>> ServletRequestListener : request init.");
    }
}
