package com.xinchen.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.xinchen.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinchen on 2017/6/11.
 */
@Controller
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

//    @RequestMapping("main")
//    public ModelAndView forwardMain(){
//        ModelAndView mav = new ModelAndView("/redis/main");
//        return mav;
//    }

    @RequestMapping("list")
    @ResponseBody
    public List<String> getList(){
        redisService.getAllKeys();
        List<String> list = new LinkedList<>(redisService.getAllKeys());
        return list;
    }
    @RequestMapping(value = "list/{name}", method = RequestMethod.GET)
    @ResponseBody
    ModelAndView getContent(@PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView("/redis/content");
        mav.addObject("content",redisService.get(name));
        return mav;
    }


}
