package com.ETU.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class JSONController {
    @GetMapping ("/getString")
    public String getString()
    {
        return "Hello World";
    }
}
