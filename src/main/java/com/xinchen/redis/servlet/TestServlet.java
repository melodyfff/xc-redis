package com.xinchen.redis.servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xinchen
 * @version 1.0
 * @date 06/12/2018 09:00
 */
@WebServlet(name = "hello", urlPatterns = {"/hello"}, loadOnStartup = 1, description = "this is a test")
public class TestServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(">>> HttpServlet : enter servlet");
        super.doGet(req, resp);
    }

}
