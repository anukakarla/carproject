package com.te.carmaintenance.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.dao.AdminDao;
import com.te.carmaintenance.dao.CarDao;

@ExtendWith(MockitoExtension.class)
class AdminSeviceImpTest {
	@Mock
	private AdminDao adminDao;
	@Mock
	private CarDetails carDetails;
	@InjectMocks
	private AdminSeviceImp adminSeviceImp;
	@Mock
	private CarDao carDao;

	@Test
	void testLoadUserByUsername() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setUsername("anu");
		admin.setPassword("qwerty");
		admin.setRole("ROLE_ADMIN");
		when(adminDao.findByUsername("anu")).thenReturn(admin);
		UserDetails loadDetails = adminSeviceImp.loadUserByUsername("anu");
		assertEquals("anu", loadDetails.getUsername());
	}

	@Test
	void testSaveSignUpData() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setUsername("anu");
		admin.setPassword("qwerty");
		admin.setRole("ROLE_ADMIN");

		when(adminDao.save(admin)).thenReturn(admin);
		Admin admin2 = adminSeviceImp.saveSignUpData(admin);
		assertEquals("anu", admin2.getUsername());
	}

	@Test
	void testGetAllCarDetails() {
		List<CarDetails> list = adminSeviceImp.getAllCarDetails();
		assertTrue(list.isEmpty());

	}

	@Test
	void testAddCarDetails() {
		CarDetails carDetails=new CarDetails();
		carDetails.setCompanyName("as");
		carDetails.setGearType("sdf");
		when(carDao.save(carDetails)).thenReturn(carDetails);
		CarDetails carDetails2=adminSeviceImp.addCarDetails(carDetails, null);
		assertEquals("as", carDetails2.getCompanyName());	
	}

	@Test
	void testUpdateCarDetails() {
		CarDetails carDetails=new CarDetails();
		carDetails.setFuelType("petrol");
		carDetails.setOnRoadPrice(56785678.87);
		when(carDao.save(carDetails)).thenReturn(carDetails);
		CarDetails details=adminSeviceImp.updateCarDetails(carDetails,null);
		assertEquals("petrol", details.getFuelType());
	}

	@Test
	void testDeleteCarDetails() {
		CarDetails carDetails=new CarDetails();

		when(carDao.findById(1)).thenReturn(carDetails);
		boolean details=adminSeviceImp.deleteCarDetails(1);
		assertTrue(details);
		
	}

	@Test
	void testGetRole() {
		Admin admin=new Admin();
		admin.setRole("ROLE_ADMIN");
		when(admin.getRole()).thenReturn("ROLE_ADMIN");
		Admin  admin2=adminSeviceImp.getrole("ROLE_ADMIN");
		assertEquals("ROLE_ADMIN", admin2.getRole());
	}

	@Test
	void testGetAdminDetails() {
		fail("Not yet implemented");
	}

}
