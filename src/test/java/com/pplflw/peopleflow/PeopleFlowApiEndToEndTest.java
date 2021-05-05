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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PeopleflowApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations =
        "classpath:application-integrationtest.properties")
class PeopleFlowApiEndToEndTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCallingPostEmployee_thenEmployeeIsSaved() throws Exception {
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
                .andExpect(status().isCreated());

        //given
        employee.setId(1);
        employee.setState(new EmployeeState().state(EmployeeState.StateEnum.IN_CHECK));
        //when
        mvc.perform(patch("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                //then
                .andExpect(status().isNoContent());
    }
}
