package com.te.carmaintenance.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class CarDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String companyName;
	private String fuelType;
	private boolean powerSteering;
	private String breakSystem;
	private Double showroomPrice;
	private Double onRoadPrice;
	private String imageUrl;
	private Double mileage;
	private int seatingCapacity;
	private int engineCapacity;
	private String gearType;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_fk", referencedColumnName = "username")
	private Admin admin;
	
	
	

}
