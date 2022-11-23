package com.github.jj_sallo.recordstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.servlet.error.ErrorController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorHandlerController implements ErrorController {
    final int NOT_FOUND = 404;
    final int METHOD_NOT_ALLOWED = 405;
    final int BAD_REQUEST = 400;
    @RequestMapping("/error")
    ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
                case NOT_FOUND:
                    return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
                case METHOD_NOT_ALLOWED:
                    return new ResponseEntity<>("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED);
                case BAD_REQUEST:
                    return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(HttpStatus.resolve(statusCode));
            }
        }
        return new ResponseEntity<>("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
