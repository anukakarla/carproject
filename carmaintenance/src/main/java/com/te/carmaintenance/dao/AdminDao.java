package com.te.carmaintenance.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.bean.CarDetails;

public interface AdminDao extends CrudRepository<Admin, Integer>{
	public Admin findByUsername(String name) ;




}
