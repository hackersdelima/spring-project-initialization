package com.peepalsoft.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peepalsoft.app.entity.Office;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Integer>{
	
	

}
