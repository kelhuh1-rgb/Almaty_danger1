package com.Almaty_danger.Almaty_danger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class quickstart {

    @GetMapping("/hello")
    public String HelloIsabek(){
        return "Hello Isabek";
    }

}
