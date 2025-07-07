package com.electronic.store.Electronic.Store.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController
{
    @GetMapping
    public String Testing()
    {
        return "Welcome to the electronic store";
    }
}
