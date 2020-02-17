package com.peepalsoft.app.controller;

import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Staffs;
import com.peepalsoft.app.entity.Users;
import com.peepalsoft.app.message.HttpResponses;
import com.peepalsoft.app.message.Messages;
import com.peepalsoft.app.repo.StaffsRepo;
import com.peepalsoft.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/userprofiles")
public class ProfileController {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    StaffsRepo staffsRepo;

    @GetMapping(path = "/user-profile")
    public ModelAndView viewprofile(Authentication authentication) {
        ModelAndView model = new ModelAndView("profiles/profile");
        model.addObject("pagetitle", "PROFILES");

        Users current_user = getCurrentUser(authentication);
        model.addObject("cuser", current_user);

        return model;
    }

    @GetMapping(path = "/user-profile/{code}")
    public ModelAndView viewprofile(@PathVariable String code) {
        ModelAndView model = new ModelAndView("profiles/userprofile");
        model.addObject("code", code);

        return model;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Staffs staffs, Authentication authentication) {
        try {
            Users current_user = getCurrentUser(authentication);
            Staffs current_staff = current_user.getStaffs();
            String current_s_code = current_staff.getCode();

            Staffs s = staffsRepo.findById(current_s_code).get();
            s.setFirstName(staffs.getFirstName());
            s.setLastName(staffs.getLastName());
            s.setPhoneNumber(staffs.getPhoneNumber());

            Staffs savedStaffs = staffsRepo.save(s);

            if (savedStaffs != null) {
                return new ResponseEntity<Messages>(HttpResponses.created(savedStaffs), HttpStatus.CREATED);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
    }

    public Users getCurrentUser(Authentication authentication) {
        String current_username = authentication.getName();
        Optional<Users> current_userOptional = usersRepo.findByUsername(current_username);
        if (current_userOptional.isPresent()) {
            return current_userOptional.get();
        }
        return new Users();
    }

    @PostMapping(path = "/change-password")
    public ResponseEntity<?> updatepassword(@RequestBody Users users, Authentication authentication) {

        String confirmPassword = users.getConfirmpassword();
        String newPassword = users.getNewpassword();

        if (confirmPassword.equals(newPassword)) {
            String username = authentication.getName();
            Optional<Users> currentUserOptional = usersRepo.findByUsername(username);
            AtomicReference<Users> currentUser = new AtomicReference<>(new Users());
            currentUserOptional.ifPresent(users1 ->
                    currentUser.set(users1)
            );
            String userpassword = currentUser.get().getPassword();

            String oldpassword = users.getPassword();
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

            if (encode.matches(oldpassword, userpassword)) {

                String newencodedPassword = encode.encode(newPassword);
                currentUser.get().setPassword(newencodedPassword);

                usersRepo.save(currentUser.get());
                return new ResponseEntity<Messages>(HttpResponses.created(currentUser), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Messages>(HttpResponses.invalidPassword(), HttpStatus.BAD_REQUEST);

            }
        }
        return new ResponseEntity<Messages>(HttpResponses.passwordMismatch(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<?> staffDetail(@PathVariable String code) {
        if (code != null) {
            try {
                Staffs staffs = staffsRepo.findById(code).get();
                if (staffs != null) {
                    byte[] image_byte = staffs.getPic();
                    if (image_byte != null) {
                        String profileimage = Base64.getEncoder().encodeToString(image_byte);
                        staffs.setBase64pic(profileimage);
                    }
                    return new ResponseEntity<Messages>(HttpResponses.fetched(staffs), HttpStatus.OK);
                }
            } catch (Exception e) {
                return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/colleagues-page")
    public ModelAndView colleagues() {
        ModelAndView model = new ModelAndView("profiles/colleagues");
        model.addObject("pagetitle", "COLLEAGUES");
        return model;
    }

    @GetMapping(path = "/colleagues")
    public ResponseEntity<?> colleaguesList(Authentication authentication) {
        String currentUsername = authentication.getName();
        Optional<Users> usersOptional = usersRepo.findByUsername(currentUsername);
        AtomicReference<Office> office = new AtomicReference<>(new Office());
        usersOptional.ifPresent(users -> {
            office.set(users.getStaffs().getOffice());
        });

        List<Staffs> list = staffsRepo.findByOffice(office.get());

        if (list != null) {
            if (list.size() > 0) {
                return new ResponseEntity<Messages>(HttpResponses.fetched(list), HttpStatus.OK);
            } else {
                return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
        }
    }


}
