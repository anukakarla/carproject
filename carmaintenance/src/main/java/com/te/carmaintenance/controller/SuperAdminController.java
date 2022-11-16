package com.te.carmaintenance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.response.SuperAdminResponse;
import com.te.carmaintenance.service.SuperAdminService;

@RestController
@CrossOrigin(origins = "*")

public class SuperAdminController {
	@Autowired
	private SuperAdminService adminService;
	
	@GetMapping("/super/getallDetails")
	public ResponseEntity<?> getall() {
		try {
			List<CarDetails> car=adminService.carDetails();
			return ResponseEntity.ok(new SuperAdminResponse("Got All details", false, car));
		} catch (Exception e) {
			return ResponseEntity.ok(new SuperAdminResponse("details are empty", false,null));

		}
		
	}

}
