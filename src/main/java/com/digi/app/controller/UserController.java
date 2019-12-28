package com.digi.app.controller;

import com.digi.app.entity.Users;
import com.digi.app.message.HttpResponses;
import com.digi.app.message.Messages;
import com.digi.app.repo.StaffsRepo;
import com.digi.app.repo.UsersRepo;
import com.digi.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    StaffsRepo staffsRepo;


    @Autowired
    UsersService usersService;


    @GetMapping(path = "/create-page")
    public ModelAndView createpage() {
        ModelAndView model = new ModelAndView("users/create");
        model.addObject("pagetitle", "USERS");
        model.addObject("staffs", staffsRepo.findAll());
        return model;
    }

    @GetMapping(path = "/view-page")
    public ModelAndView viewpage() {
        ModelAndView model = new ModelAndView("users/view");
        model.addObject("pagetitle", "USERS");
        return model;
    }

    @GetMapping(path = "/specificUsers")
    public ResponseEntity<?> read(@ModelAttribute("username") String currentUsername) {
        ResponseEntity<?> returntype = usersService.findByCurrentUsersStaffsOffice(currentUsername);
        return returntype;

    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<?> readOne(@PathVariable String username) {
        try {
            Users users = usersRepo.findByUsername(username);

            if (users != null) {
                return new ResponseEntity<Users>(users, HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Messages> create(@RequestBody Users users) {
        try {
            Users savedUsers = usersService.changedPwdUsers(users);

            if (savedUsers != null) {
                return new ResponseEntity<Messages>(HttpResponses.created(savedUsers), HttpStatus.CREATED);
            }
        } catch (Exception e) {
        }
        return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Messages> delete(@PathVariable int id) {
        Users users = usersRepo.findById(id).get();

        if (users != null) {
            usersRepo.deleteById(id);
            return new ResponseEntity<Messages>(HttpResponses.received(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
        }
    }

}
