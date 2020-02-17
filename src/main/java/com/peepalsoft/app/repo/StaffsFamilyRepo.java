package com.peepalsoft.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peepalsoft.app.entity.StaffsFamily;

@Repository
public interface StaffsFamilyRepo extends JpaRepository<StaffsFamily, Integer>{
	public List<StaffsFamily> findByStaffsCode(String code);

}
