package com.te.carmaintenance.dao;


import org.springframework.data.repository.CrudRepository;

import com.te.carmaintenance.bean.CarDetails;

public interface CarDao extends CrudRepository<CarDetails, Integer> {
	public CarDetails findByCompanyName(String companyName);

	public CarDetails findByName(String name);

	public CarDetails findByFuelType(String fuelType);

	public CarDetails findById(int id);

	// List<CarDetails> allCarDetails();

//	@Query("SELECT c FROM CarDetails c WHERE c.companyName LIKE %?1%"
//            + " OR c.name LIKE %?1%"
//            + " OR c.fuelType LIKE %?1%"
//            + " OR CONCAT(c.id, '') LIKE %?1%")
//	public List<CarDetails> search(String keyword) ;

}
