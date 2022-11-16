package com.te.carmaintenance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.dao.AdminDao;
import com.te.carmaintenance.jwtutil.JwtUtil;
import com.te.carmaintenance.request.AdminRequest;
import com.te.carmaintenance.response.AdminResponse;
import com.te.carmaintenance.response.CarResponse;
import com.te.carmaintenance.service.AdminService;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200/")
public class AdminController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginAuthenticationToken(@RequestBody AdminRequest adminRequest, String role) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(adminRequest.getUsername(), adminRequest.getPassword()));
		} catch (AuthenticationException e) {
			return ResponseEntity.ok(new AdminResponse("invalid username and password", true, null,null));
		}
		UserDetails details = userDetailsService.loadUserByUsername(adminRequest.getUsername());
		Admin findrole=adminService.getrole(details.getUsername());
		String jwtToken = jwtUtil.generateToken(details);
		return ResponseEntity.ok(new AdminResponse("Authentication success", false, jwtToken,findrole.getRole()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUpAuthentication(@RequestBody Admin admin) {
		Admin signUpData = adminService.saveSignUpData(admin);
		if (signUpData != null) {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signUpData.getUsername(), signUpData.getPassword()));
			UserDetails details = userDetailsService.loadUserByUsername(signUpData.getUsername());
			String jwtToken = jwtUtil.generateToken(details);
			return ResponseEntity.ok(new AdminResponse("signUp success", false, null,null));

		} else {
			return ResponseEntity.ok(new AdminResponse("username already exists", true, null,null));
		}

	}

	@GetMapping("/getallcars")
	public ResponseEntity<?> getAllCarDetails() {
		try {
			List<CarDetails> cars = adminService.getAllCarDetails();
			return ResponseEntity.ok(new CarResponse("Got all details", false, cars));
		} catch (Exception e) {
			return ResponseEntity.ok(new CarResponse("Something went wrong", true, null));
		}

	}

	@PostMapping("/addcar")
	public ResponseEntity<?> addcarDetails(@RequestBody CarDetails details, HttpServletRequest request) {
		try {
			adminService.addCarDetails(details, request);
			return ResponseEntity.ok(new CarResponse("added succesfully", false, null));

		} catch (Exception e) {
			return ResponseEntity.ok(new CarResponse("something went wrong!", true, null));

		}

	}

	@PutMapping("/updatecar/{id}")
	public ResponseEntity<?> updateCarDetails(@RequestBody CarDetails details, HttpServletRequest request,
			@PathVariable int id) {
		try {
			details.setId(id);
			adminService.updateCarDetails(details, request);
			return ResponseEntity.ok(new CarResponse("updated successfully", false, null));
		} catch (Exception e) {
			return ResponseEntity.ok(new CarResponse("something went wrong!", true, null));
		}

	}

	@DeleteMapping("/deletecar/{id}")
	public ResponseEntity<?> deleteCarDetails(@PathVariable int id) {
		try {
			adminService.deleteCarDetails(id);
			return ResponseEntity.ok(new CarResponse("deleted succesfully", false, null));
		} catch (Exception e) {
			return ResponseEntity.ok(new CarResponse("something went wrong", true, null));

		}

	}
//	@GetMapping("/search/{id}")
//	public ResponseEntity<?> search(@PathVariable int id) {
//		try {
//			adminService.search(id);
//			return ResponseEntity.ok(new CarResponse("deleted succesfully", false,null));
//
//		} catch (Exception e) {
//			return ResponseEntity.ok(new CarResponse("data not found", false, null));
//
//		}
//		
//		
//	}
}
