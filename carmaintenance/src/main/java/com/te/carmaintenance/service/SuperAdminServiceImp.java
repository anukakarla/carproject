package com.te.carmaintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.carmaintenance.bean.CarDetails;
import com.te.carmaintenance.dao.CarDao;

@Service
public class SuperAdminServiceImp  implements SuperAdminService{
	@Autowired
	private CarDao carDao;

	@Override
	public List<CarDetails> carDetails() {
		return (List<CarDetails>) carDao.findAll();
	}

	

}
