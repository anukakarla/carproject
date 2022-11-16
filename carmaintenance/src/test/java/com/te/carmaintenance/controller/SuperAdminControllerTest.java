package com.te.carmaintenance.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.response.SuperAdminResponse;
import com.te.carmaintenance.service.SuperAdminService;
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class SuperAdminControllerTest {
	
	@MockBean
	private SuperAdminService adminService;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	
	private ObjectMapper mapper = new ObjectMapper();
 
	
	
	
	

	@BeforeEach
	void setUp() throws Exception {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testGetall() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		
//		MockHttpServletRequest httpServletRequest=new MockHttpServletRequest();
//		httpServletRequest.addHeader("Authorization","Bearer ");
		List<CarDetails> details=adminService.carDetails();
		when(adminService.carDetails()).thenReturn(details);
		String content = mockMvc
				.perform(get("/super/getallDetails").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		SuperAdminResponse response = mapper.readValue(content, SuperAdminResponse.class);
		System.out.println("result" + content);
		assertEquals("Got All details", response.getMessage());
	}

}
