package com.xinchen.redis.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * annotated classes : @WebServlet, @WebFilter, and @WebListener
 * can be automatically registered with an embedded servlet container by
 * annotating a @Configuration class with @ServletComponentScan
 * the @ServletComponentScan scans from the package of the annotated class
 *
 * @author xinchen
 * @version 1.0
 * @date 06/12/2018 09:14
 */
@Configuration
@ServletComponentScan
public class ServletConfig {
}
