package com.wanderingzj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wangzhongjiezhongjie
 * @since 2017/6/28
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.wanderingzj.cola.controller.*Controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer();
        for (Object arg : joinPoint.getArgs()) {
            sb.append("arg:" + arg.toString() + "|");
        }
        logger.info("before method:" + sb.toString());
        logger.info("before method:" + new Date());
    }

    @After("execution(* com.wanderingzj.cola.controller.*Controller.*(..))")
    public void afterMethod() {
        logger.info("before method:" + new Date());
    }
}
