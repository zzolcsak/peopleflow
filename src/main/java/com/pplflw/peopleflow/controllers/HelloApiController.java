package com.pplflw.peopleflow.controllers;

import com.pplflw.peopleflow.api.HelloApi;
import com.pplflw.peopleflow.models.Hello;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HelloApiController implements HelloApi {

    public static final String HELLO_RESPONSE = "Hello World from our custom implemented controller!";

    @Override
    public ResponseEntity<Hello> hello() {
        return new ResponseEntity<>(new Hello().text(HELLO_RESPONSE),
                HttpStatus.OK);
    }
}
