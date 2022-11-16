package com.te.carmaintenance.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.bean.MyAdminDetails;
import com.te.carmaintenance.dao.AdminDao;
import com.te.carmaintenance.jwtutil.JwtUtil;
import com.te.carmaintenance.request.AdminRequest;
import com.te.carmaintenance.response.AdminResponse;
import com.te.carmaintenance.response.CarResponse;
import com.te.carmaintenance.service.AdminService;

import ch.qos.logback.core.status.Status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AdminControllerTest {
	@MockBean
	private AdminService service;

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserDetailsService userDetailsService;

	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@MockBean
	private AdminDao adminDao;

	@Autowired
	private JwtUtil jwtUtil;

	@BeforeEach
	void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 * @throws RuntimeException
	 */
	@Test
	void testLoginAuthenticationToken() throws JsonProcessingException, UnsupportedEncodingException, Exception ,RuntimeException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWdhdmVuaSIsImV4cCI6MTY0NjYyMjg5OCwiaWF0IjoxNjQ2NTg2ODk4fQ.VnJPuNy6BD1lRjcyfnMTR9ZgZhcqd5uWaEjomAmHbUg");
		Admin admin = new Admin();
		admin.setId(100);
		admin.setUsername("anu");
		admin.setPassword("qwerty");
		admin.setRole("ROLE_ADMIN");
		adminDao.save(admin);
//		System.out.println(adminDao.findByUsername(admin.getUsername()));
//		Admin findByUsername = adminDao.findByUsername(admin.getUsername());
		AdminRequest adminRequest = new AdminRequest(admin.getUsername(), admin.getPassword());
		MyAdminDetails details = new MyAdminDetails(admin);
		System.out.println(details);
//		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminRequest.getUsername(), adminRequest.getPassword()));
//		when(authenticationManager.authenticate())
		System.out.println(details);
//		lenient().doNothing().when(authenticationManager.authenticate(Mockito.any()))/* .thenReturn(null) */;
		System.out.println("abc");
		when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(details);
//		when(service.getrole("ROLE_ADMIN")).thenReturn(admin);
		when(service.getAdminDetails("anu")).thenReturn(admin);

		when(jwtUtil.generateToken(details)).thenReturn(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWdhdmVuaSIsImV4cCI6MTY0NjYyMjg5OCwiaWF0IjoxNjQ2NTg2ODk4fQ.VnJPuNy6BD1lRjcyfnMTR9ZgZhcqd5uWaEjomAmHbUg");
		String content = mvc
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		AdminResponse response = mapper.readValue(content, AdminResponse.class);
		System.out.println("result" + content);
		assertEquals("Authentication success", response.getMessage());

	}

	@Test
	@Disabled
	void testSignUpAuthentication() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		Admin admin = new Admin();
		admin.setId(2);
		admin.setUsername("anu");
		admin.setPassword("qwerty");
		admin.setRole("ROLE_ADMIN");
		when(service.saveSignUpData(admin)).thenReturn(admin);
		when(authenticationManager.authenticate(Mockito.any())).thenReturn(null);
		
		MyAdminDetails details = new MyAdminDetails(admin);
		when(userDetailsService.loadUserByUsername("anu")).thenReturn(details);
		when(service.getAdminDetails("anu")).thenReturn(admin);
		when(jwtUtil.generateToken(Mockito.any())).thenReturn("sdfghj");
		String content = mvc
				.perform(post("/signup").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		AdminResponse response = mapper.readValue(content, AdminResponse.class);
		System.out.println("result" + content);
		assertEquals("signUp success", response.getMessage());

		
	}

	@Test
	@Disabled
	void testGetAllCarDetails() throws JsonProcessingException, UnsupportedEncodingException, Exception {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		request.addHeader("Authorization",
//				"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWdhdmVuaSIsImV4cCI6MTY0NjYyMjg5OCwiaWF0IjoxNjQ2NTg2ODk4fQ.VnJPuNy6BD1lRjcyfnMTR9ZgZhcqd5uWaEjomAmHbUg");
		List<CarDetails> details = service.getAllCarDetails();
		when(service.getAllCarDetails()).thenReturn((List<CarDetails>) details);
		String content = mvc
				.perform(get("/getallcars").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		CarResponse response = mapper.readValue(content, CarResponse.class);
		System.out.println("result" + content);
		assertEquals("Got all details", response.getMessage());

	}

	@Test
	@Disabled
	void testAddcarDetails() throws UnsupportedEncodingException, Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWdhdmVuaSIsImV4cCI6MTY0NjYyMjg5OCwiaWF0IjoxNjQ2NTg2ODk4fQ.VnJPuNy6BD1lRjcyfnMTR9ZgZhcqd5uWaEjomAmHbUg");
		CarDetails details = new CarDetails();
		details.setName("Maruti Swift");
		details.setCompanyName("Maruti Suzuki");
		details.setFuelType("Petrol");
		details.setSeatingCapacity(4);
		details.setBreakSystem("Anti-Lock");
		details.setGearType("Knob");
		details.setEngineCapacity(1197);
		details.setMileage(23.4);
		details.setOnRoadPrice(5600000.65);
		details.setShowroomPrice(56789234.87);
		details.setImageUrl(
				"data:image/jpeg;base64,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");
		when(service.addCarDetails(details, request)).thenReturn(details);

		String content = mvc
				.perform(post("/addcar").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		AdminResponse response = mapper.readValue(content, AdminResponse.class);
		System.out.println("result" + content);
		assertEquals("added succesfully", response.getMessage());

	}

	@Test
	@Disabled
	void testUpdateCarDetails() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer ");
		CarDetails details = new CarDetails();
		details.setName("Maruti Swift");
		details.setCompanyName("Maruti Suzuki");
		details.setFuelType("Petrol");
		details.setSeatingCapacity(4);
		details.setId(24);
		when(service.updateCarDetails(details, request)).thenReturn(details);

		String content = mvc
				.perform(put("/updatecar/24").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		CarResponse response = mapper.readValue(content, CarResponse.class);

		System.out.println("result" + content);
		assertEquals("updated successfully", response.getMessage());

	}

	@Test
	@Disabled
	void testDeleteCarDetails() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		CarDetails details=new CarDetails();
		details.setId(2);
		
		String content = mvc
				.perform(delete("/deletecar/2").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(details)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		CarResponse response = mapper.readValue(content, CarResponse.class);

		System.out.println("result" + content);
		assertEquals("deleted succesfully", response.getMessage());
		
	}

}
