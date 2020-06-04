package com.godcheese.example4;

import com.gioov.exampleutil.ExampleUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2020-06-04
 */
@RestController
public class ExampleRestController {

    @RequestMapping("/test")
    public String test() {
        return ExampleUtil.test();
    }
}
