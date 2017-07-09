package com.wanderingzj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangzhongjiezhongjie
 * @since 2017/6/28
 */
@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(path = {"/test"})
    @ResponseBody
    public Object test() {
        logger.info("TEST");
        return "Test Page";
    }

}
