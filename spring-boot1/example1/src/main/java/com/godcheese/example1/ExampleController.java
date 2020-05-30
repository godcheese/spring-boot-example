package com.godcheese.example1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2020-05-23
 */
@Controller
public class ExampleController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
