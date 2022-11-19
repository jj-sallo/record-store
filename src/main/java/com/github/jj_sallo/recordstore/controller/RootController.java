package com.github.jj_sallo.recordstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {
    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
    }
}
