package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(HttpSession session) {
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        String result = "";
        for (String msg:messages) {
            result += msg;
        }
        return result;
    }
    @PostMapping("/")
    public String persistMessage(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        }
        messages.add("Hello;");
        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        return "ok";
    }

    @DeleteMapping("/")
    public String deleteMessages(HttpServletRequest request) {
        request.getSession().removeAttribute("MY_SESSION_MESSAGES");
        return "ok";
    }
}