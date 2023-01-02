package com.chriscaron.demo.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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

    String studentJson = """
                {
                    "id": 1,
                    "name": "Chris",
                    "email": "chris@fakeemail.com",
                    "dob": "1993-11-15",
                    "age": 29
                }""";

    String studentListJson = """
                [
                    {
                        "id": 1,
                        "name": "Chris",
                        "email": "chris@fakeemail.com",
                        "dob": "1993-11-15",
                        "age": 29
                    }
                ]""";

    @Test
    void getStudents() throws Exception {
        students.add(student);
        Mockito.when(studentService.getStudents()).thenReturn(students);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/student")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(studentListJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    void registerNewStudent() throws Exception {
        Student student = new Student(1L,"Chris", "chris@fakeemail.com", LocalDate.of(1993, NOVEMBER, 15));

        Mockito.when(studentService.addNewStudent(Mockito.any(Student.class))).thenReturn(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/student")
                .accept(MediaType.APPLICATION_JSON)
                .content(studentJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/api/v1/student", response.getHeader(HttpHeaders.LOCATION));
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