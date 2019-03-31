package com.helloworld.cicd;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.cicd.dto.Employee;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloWorldController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void sayHello() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello people")));
	}

	@Test
	public void getEmployees() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].employeeNumber", is(41)))
				.andExpect(jsonPath("$[0].employeeName", is("User3")));
	}

	@Test
	public void addEmployees() throws Exception {
		Employee emp = new Employee();
		emp.setEmployeeName("User1");
		emp.setEmployeeDept("CS");
		emp.setEmployeeNumber(21l);
		emp.setEmployeeSupervisor("Fransic");

		mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Employee Added Successfully")));

		emp = new Employee();
		emp.setEmployeeName("User2");
		emp.setEmployeeDept("IT");
		emp.setEmployeeNumber(31l);
		emp.setEmployeeSupervisor("Fransic");

		mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(emp)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Employee Added Successfully")));
	}
}
