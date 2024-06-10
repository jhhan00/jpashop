package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j      //로거를 찍을 수 있게 됨
public class HomeController {

//    Logger logger = LoggerFactory.getLogger(getClass());    // 기존의 로거

    @RequestMapping("/")
    public String home() {
        log.info("home controller ~");
        return "home";
    }
}
