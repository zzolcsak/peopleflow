package com.pplflw.peopleflow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplflw.peopleflow.models.Employee;
import com.pplflw.peopleflow.models.EmployeeState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PeopleflowApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations =
        "classpath:application-integrationtest.properties")
public class PeopleFlowApiEndToEndTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenCallingPostEmployee_thenEmployeeIsSaved() throws Exception {
        //given
        Employee employee = new Employee()
                .age(66)
                .name("John W. Smith")
                .contractInformation("info");

        //when
        mvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))

                //then
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name")
                        .value("John W. Smith"))
                .andExpect(jsonPath("$.contractInformation")
                        .value("info"))
                .andExpect(jsonPath("$.state.state")
                        .value(EmployeeState.StateEnum.ADDED.getValue()));
    }
}
