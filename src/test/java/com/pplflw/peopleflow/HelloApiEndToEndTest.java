package com.pplflw.peopleflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.pplflw.peopleflow.controllers.HelloApiController.HELLO_RESPONSE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PeopleflowApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations =
        "classpath:application-integrationtest.properties")
public class HelloApiEndToEndTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenCallingGetHello_thenReturnsPredefinedStringWithOk()
            throws Exception {
        mvc.perform(get("hello")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(HELLO_RESPONSE));
    }
}
