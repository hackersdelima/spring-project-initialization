package com.peepalsoft.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Staffs;

@Repository
public interface StaffsRepo extends JpaRepository<Staffs, String>{
	public List<Staffs> findByOffice(Office office);

}
