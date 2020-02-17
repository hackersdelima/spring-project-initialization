package com.peepalsoft.app.util;

import com.peepalsoft.app.auth.Role;
import com.peepalsoft.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Utilities {
    @Autowired
    UsersRepo usersRepo;

    public List<String> currentUserRoles(Principal principal){
       Collection<Role> roles=usersRepo.findByUsername(principal.getName()).get().getRoles();
       List<String> rolesList=new ArrayList<>();
       for(Role role: roles){
           rolesList.add(role.getName());
       }
        return rolesList;
    }
    public String currentUsername(Principal principal){
        return principal.getName();
    }
}
