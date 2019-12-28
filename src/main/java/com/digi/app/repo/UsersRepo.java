package com.digi.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digi.app.entity.Office;
import com.digi.app.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer>{
	public Users findByUsername(String username);
	public List<Users> findByStaffsOffice(Office office);

}
