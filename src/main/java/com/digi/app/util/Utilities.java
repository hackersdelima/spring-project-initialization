package com.digi.app.util;

import com.digi.app.auth.Role;
import com.digi.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
       Collection<Role> roles=usersRepo.findByUsername(principal.getName()).getRoles();
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
