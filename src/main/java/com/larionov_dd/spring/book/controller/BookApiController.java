package com.larionov_dd.spring.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookApiController {

    @GetMapping("/")
    public String ok(){
        return "ok";
    }

}
