package com.peepalsoft.app;

import com.peepalsoft.app.auth.Role;
import com.peepalsoft.app.entity.Users;
import com.peepalsoft.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    UsersRepo usersRepo;

    @ModelAttribute(name = "profileimage")
    public String profilepic(Authentication authentication) {
        String profileimage = "";
        if (authentication != null) {
            Users current_user = getCurrentUser(authentication);
            byte[] image_byte = current_user.getStaffs().getPic();
            if (image_byte != null) {
                profileimage = Base64.getEncoder().encodeToString(image_byte);
            }
        }
        return profileimage;
    }

    @ModelAttribute(name = "username")
    public String username(Authentication authentication) {
        String username = "";
        try {
            Users current_user = getCurrentUser(authentication);
            if (current_user != null) {
                username = current_user.getUsername();
            }
        } catch (Exception e) {

        }

        return username;
    }

    //returns the username of the current user
    public Users getCurrentUser(Authentication authentication) {
        String current_username = authentication.getName();
        Optional<Users> current_userOptional = usersRepo.findByUsername(current_username);
        if (current_userOptional.isPresent()) {
            return current_userOptional.get();
        }
        return new Users();
    }

    //getting user role
    @ModelAttribute(name = "role")
    public Collection<Role> roleOfUser(Authentication authentication) {
        Collection<Role> user_role = new ArrayList<Role>();
        try {
            Users current_user = getCurrentUser(authentication);
            if (current_user != null) {
                user_role = current_user.getRoles();
            }
        } catch (Exception e) {
        }
        return user_role;
    }

    //getting user post
    @ModelAttribute(name = "post")
    public String getuserPost(Authentication authentication) {
        String post = "";
        try {
            Users current_user = getCurrentUser(authentication);
            if (current_user != null) {
                post = current_user.getStaffs().getPost();
            }
        } catch (Exception e) {
        }
        return post;
    }
}
