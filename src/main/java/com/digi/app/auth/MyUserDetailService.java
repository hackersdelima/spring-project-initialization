package com.digi.app.auth;

import com.digi.app.entity.Users;
import com.digi.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/*
POWERED BY PEEPALSOFT - SHISHIR KARKI
 */
@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User 404");
        //using spring security's User class instead of UserPrincipal
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    //get name of roles
    private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
