package com.chriscaron.demo.student;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    Student student = new Student(1L,"Chris", "chris@fakeemail.com", LocalDate.of(1993, NOVEMBER, 15));

    List<Student> students = new ArrayList<>();

    @Test
    void getStudents() throws Exception {
        students.add(student);
        Mockito.when(studentService.getStudents()).thenReturn(students);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/student").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = """
                [
                    {
                        "id": 1,
                        "name": "Chris",
                        "email": "chris@fakeemail.com",
                        "dob": "1993-11-15",
                        "age": 29
                    }
                ]""";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void registerNewStudent() {
        // TODO
    }

    @Test
    void updateStudent() {
        // TODO
    }

    @Test
    void deleteStudent() {
        // TODO
    }
}