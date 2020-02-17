package com.peepalsoft.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Office {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min = 2, max = 8)
	private String ro_code;
	
	@NotNull
	@Size(min = 2, max = 2)
	private String office_level;
	
	@NotNull
	@Size(min = 4, max = 30)
	private String name;
	
	@NotNull
	@Size(min = 4, max = 30)
	private String address;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRo_code() {
		return ro_code;
	}
	public void setRo_code(String ro_code) {
		this.ro_code = ro_code;
	}
	
	public String getOffice_level() {
		return office_level;
	}
	public void setOffice_level(String office_level) {
		this.office_level = office_level;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
