package com.digi.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digi.app.entity.Office;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Integer>{
	
	

}
