package com.Almaty_danger.Almaty_danger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;



@SpringBootApplication
public class AlmatyDangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                AlmatyDangerApplication.class,
                args
        );
    }
}