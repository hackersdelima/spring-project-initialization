package com.peepalsoft.app.repo;

import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

    List<Users> findByStaffsOffice(Office office);

}
