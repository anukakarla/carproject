package com.te.carmaintenance.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.bean.CarDetails;

public interface AdminService {

	Admin saveSignUpData(Admin admin);

	List<CarDetails> getAllCarDetails();
	
	Admin getAdminDetails(String username);

	CarDetails addCarDetails(CarDetails details, HttpServletRequest request);

	CarDetails updateCarDetails(CarDetails details, HttpServletRequest request);

	boolean deleteCarDetails(int id);
	
	Admin getrole(String role);

	
	
	
//	boolean search(int id);

}
