package com.wanderingzj.controller;

import com.wanderingzj.model.Friend;
import com.wanderingzj.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;


/**
 * @author wangzhongjiezhongjie
 * @since 2017/6/28
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    LoginService loginService;

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public Object index(Model model, HttpSession httpSession) {
        logger.info("HOME");
        model.addAttribute("user", "wzj");
        model.addAttribute("gender",1);//gender:性别，1：男；0：女；
        model.addAttribute("loginInfo", loginService.getMessage());
        Friend[] friends = new Friend[2];
        friends[0] = new Friend("Jack Ma", 50);
        friends[1] = new Friend("Pony Ma", 49);
        model.addAttribute("friends", friends);
        if (httpSession.getAttribute("msg") != null) {
            model.addAttribute("httpMsg", httpSession.getAttribute("msg").toString());
        } else {
            model.addAttribute("httpMsg", "");
        }
        return "index";
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", required = false) String key) {
        return String.format("Profile Page of %s / %d, t:%d k: %s", groupId, userId, type, key);
    }

    @RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
    public RedirectView redirect(@PathVariable("code") int code,
                                 HttpSession httpSession) {
        httpSession.setAttribute("msg", "jump from redirect");
        RedirectView red = new RedirectView("/", true);
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red;
    }

    @RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletResponse response,
                          HttpServletRequest request,
                          HttpSession httpSession,
                          @CookieValue("JSESSIONID") String sessionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("COOKIEVALUE:" + sessionId);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                sb.append("Cookie:" + cookie.getName() + " value:" + cookie.getValue());
            }
        }
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        sb.append(request.getRequestURI() + "<br>");

        response.addHeader("useID", "1");
        response.addCookie(new Cookie("username", "wzj"));

        return sb.toString();
    }

    @RequestMapping(path = {"/group"}, method = {RequestMethod.GET})
    @ResponseBody
    public String admin(@RequestParam("type") String key) {
        if ("admin".equals(key)) {
            return "hello admin";
        }
        throw new IllegalArgumentException("无权限");
    }

    @ExceptionHandler()
    @ResponseBody
    public String error(Exception e) {
        return e.getMessage();
    }
}
