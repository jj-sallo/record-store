package com.github.jj_sallo.recordstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @RequestMapping
    public ResponseEntity<String> rootDir() {
        return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
    }

    @RequestMapping("/api")
    public ResponseEntity<String> apiDir() {
        return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
    }
}
