package com.peepalsoft.app.component;

import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Users;
import com.peepalsoft.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UsersComponent {
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    CrudReturnService<Users> crudReturnService;

    public List<Users> findUsersByCurrentUsersStaffsOffice(String currentUsername) {
        Office currentUserOffice = findOfficeByCurrentUsers(currentUsername);
        // if role admin find all
        List<Users> list = usersRepo.findByStaffsOffice(currentUserOffice);
        return list;
    }

    public ResponseEntity<?> getReturn(List<Users> list) {
        ResponseEntity<?> returntype = crudReturnService.controllerReturnForList(list);
        return returntype;
    }

    public Office findOfficeByCurrentUsers(String currentUsername) {
        Optional<Users> currentUserOptional = usersRepo.findByUsername(currentUsername);
        AtomicReference<Office> currentUserOffice = new AtomicReference<>(new Office());
        currentUserOptional.ifPresent(users -> {
            currentUserOffice.set(users.getStaffs().getOffice());
        });
        return currentUserOffice.get();
    }
}
