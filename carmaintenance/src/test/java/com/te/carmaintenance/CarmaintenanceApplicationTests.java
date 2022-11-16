package com.te.carmaintenance;

//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.booleanThat;
//
//import java.util.List;
//
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.annotation.Order;
//
//import com.te.carmaintenance.bean.Admin;
//import com.te.carmaintenance.bean.CarDetails;
//import com.te.carmaintenance.dao.AdminDao;
//import com.te.carmaintenance.dao.CarDao;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class CarmaintenanceApplicationTests {

//	@Autowired
//	private AdminDao dao;
//
//	private CarDao carDao;

//	@Test
//	public void addCarDetails() {
//
//		CarDetails admin = new CarDetails();
//		admin.setBreakSystem("dfgh");
//		admin.setCompanyName("bmw");
//		admin.setName("bmw");
//
//		CarDetails admin2 = carDao.save(admin);
//
//	}
//
//	@Test
////	@org.junit.jupiter.api.Order(value = 1)
//	@Order(1)
//	public void getAllCarDetails() {
//		List<Admin> list = (List<Admin>) dao.findAll();
//		assertThat(list).size().isGreaterThan(0);
//
//	}

//	@Test
////	@org.junit.jupiter.api.Order(value = 2)
//	@Order(2)
//	public void deleteCarDetails() {
//		dao.deleteById(23);
//		assertThat(dao.existsById(23)).isFalse();
//
//	}

//	@Test
//	@Order(2)
//	public void deleteCarDetails() {
//		int id=3;
//		boolean isbefore=dao.findById(3).isPresent();
//		dao.deleteById(23);
//		boolean notexistafter=dao.findById(3).isPresent();
//		assertTrue(isexistbefore);
//		assertFalse(notexistafter);
//
//		
//	}

}
