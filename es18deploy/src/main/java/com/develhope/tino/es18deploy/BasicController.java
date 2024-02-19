package com.develhope.tino.es18deploy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BasicController {

    @GetMapping("/randomsum")
    public String sumRandomIntegers() {
        Random random = new Random();
        int num1 = random.nextInt(128);
        int num2 = random.nextInt(128);
        int sum = num1 + num2;
        return num1 + " + " + num2 + " = " + sum;
    }

}
