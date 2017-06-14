package com.xinchen.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xinchen on 2017/6/14.
 */
@Controller
public class indexController {
    @RequestMapping(value = "/{system}/{index}",method = RequestMethod.GET)
    public String index(@PathVariable("system") String system,
                        @PathVariable("index") String index){
        return system+"/"+index;
    }

}
