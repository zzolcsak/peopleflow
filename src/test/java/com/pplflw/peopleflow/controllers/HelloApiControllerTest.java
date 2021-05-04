package com.pplflw.peopleflow.controllers;


import com.pplflw.peopleflow.models.Hello;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.pplflw.peopleflow.controllers.HelloApiController.HELLO_RESPONSE;

public class HelloApiControllerTest {
    @Test
    public void whenCallingHello_thenReturnsExactString() {
        ResponseEntity<Hello> response = new HelloApiController().hello();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(HELLO_RESPONSE, Objects.requireNonNull(response
                .getBody())
                .getText());
    }
}
