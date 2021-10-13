package org.y3s.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.y3s.Member;
import org.y3s.service.HelloService;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    public void hello() {
        Member member = helloService.logic();
    }

}
