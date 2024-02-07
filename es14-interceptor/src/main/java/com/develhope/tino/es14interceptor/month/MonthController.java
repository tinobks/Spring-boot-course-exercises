package com.develhope.tino.es14interceptor.month;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/months")
public class MonthController {

    @GetMapping
    public MonthEntity getMonth(@RequestAttribute("month") MonthEntity month) {
        return month;
    }
}
