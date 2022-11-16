package com.te.carmaintenance.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.bean.MyAdminDetails;
import com.te.carmaintenance.dao.AdminDao;
import com.te.carmaintenance.dao.CarDao;
import com.te.carmaintenance.jwtutil.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminSeviceImp implements UserDetailsService, AdminService {
	@Autowired
	private AdminDao admindao;

	@Autowired
	private CarDao cardao;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = admindao.findByUsername(username);
		if (admin != null) {
			return new MyAdminDetails(admin);

		} else {
			throw new UsernameNotFoundException("username not found for this  " + username);
		}
	}

	@Override
	public Admin saveSignUpData(Admin admin) {
		return admindao.save(admin);
	}

	@Override
	public List<CarDetails> getAllCarDetails() {
		return (List<CarDetails>) cardao.findAll();
	}

//	@Override
//	public CarDetails addCarDetails(CarDetails details, HttpServletRequest request) {
//		 String tokenHeader=request.getHeader("Authorization");
//		String token =tokenHeader.substring(7);
//		String adminName=jwtUtil.extractUsername(token);
//		Admin admin=admindao.findByUsername(adminName);
//		double OriginalShowroomPrice=(double)(details.getShowroomPrice()*100000);
//		  double originalOnRoadPrice=0;
//		  details.setShowroomPrice(OriginalShowroomPrice);
//		  if (details.getFuelType().equals("Electric")) {
//			 originalOnRoadPrice = (Double)details.getShowroomPrice()*1.04;
//			
//		}
//		  else if (details.getShowroomPrice()<500000) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.13;
//			
//		}
//		  else if (details.getShowroomPrice()<500000 && details.getShowroomPrice()<1000000) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.14;
//			
//		}
//		  else if (details.getShowroomPrice()<1000000 && details.getShowroomPrice()<2000000) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.17;
//			
//		}
//		  else if (details.getShowroomPrice()>2000000 ) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.18;
//			
//		}
//		  details.setShowroomPrice(originalOnRoadPrice);
//		  details.setAdminId(admin);
//		  
//		
//		return cardao.save(details);
//	}

//	@Override
//	public CarDetails updateCarDetails(CarDetails details, HttpServletRequest request) {
//		String tokenHeader=request.getHeader("Authorization");
//		String token =tokenHeader.substring(7);
//		String adminName=jwtUtil.extractUsername(token);
//		Admin admin=admindao.findByUsername(adminName);
//		double OriginalShowroomPrice=(double)(details.getShowroomPrice()*100000);
//		  double originalOnRoadPrice=0;
//		  details.setShowroomPrice(OriginalShowroomPrice);
//		  if (details.getFuelType().equals("Electric")) {
//			 originalOnRoadPrice = (Double)details.getShowroomPrice()*1.04;
//			
//		}
//		  else if (details.getShowroomPrice()<500000) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.13;
//			
//		}
//		  else if (details.getShowroomPrice()<500000 && details.getShowroomPrice()<1000000) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.14;
//			
//		}
//		  else if (details.getShowroomPrice()<1000000 && details.getShowroomPrice()<2000000) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.17;
//			
//		}
//		  else if (details.getShowroomPrice()>2000000 ) {
//			  originalOnRoadPrice=details.getShowroomPrice()*1.18;
//			
//		}
//		  details.setShowroomPrice(originalOnRoadPrice);
//		  details.setAdminId(admin);
//		  
//		
//		return cardao.save(details);
//	}

//	@Override
//	public boolean deleteCarDetails(int carId) {
//	CarDetails carDetails = cardao.findById(carId);
//		System.out.println(carDetails);
//		if (carDetails != null) {
//			cardao.delete(carDetails);
//			return true;
//
//		} else {
//			return false;
//
//		}
//
//	}

	@Override
	public CarDetails addCarDetails(CarDetails details, HttpServletRequest request) {
		return cardao.save(details);
	}

	@Override
	public CarDetails updateCarDetails(CarDetails details1, HttpServletRequest request) {
		return cardao.save(details1);
	}

	@Override
	public boolean deleteCarDetails(int id) {
		CarDetails carDetails=cardao.findById(id);
         System.out.println(carDetails);
         if (carDetails!=null) {
        	 cardao.delete(carDetails);
        	 return true;
			
		}
		return false;
	}

	@Override
	public Admin getrole(String role) {
		return admindao.findByUsername(role);
	}

	@Override
	public Admin getAdminDetails(String username) {
		return admindao.findByUsername(username);
	}

	
//	@Override
//	public CarDetails search(int id) {
//		return cardao.findById(id);
//	}

}
