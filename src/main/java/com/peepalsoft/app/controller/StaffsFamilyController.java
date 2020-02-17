package com.peepalsoft.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peepalsoft.app.component.CrudReturnService;
import com.peepalsoft.app.entity.StaffsFamily;
import com.peepalsoft.app.message.HttpResponses;
import com.peepalsoft.app.message.Messages;
import com.peepalsoft.app.repo.StaffsFamilyRepo;

@RestController
@RequestMapping("staffsFamily")
public class StaffsFamilyController {
	@Autowired
	StaffsFamilyRepo staffsFamilyRepo;
	
	@Autowired
	CrudReturnService<StaffsFamily> crudReturnService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody StaffsFamily staffsFamily) {
		try {
		
		StaffsFamily savedStaffFamily=staffsFamilyRepo.save(staffsFamily);
		
		if(savedStaffFamily!=null) {
			return new ResponseEntity<Messages>(HttpResponses.created(savedStaffFamily), HttpStatus.CREATED);
		}
		
		}
		catch (Exception e) {
			System.out.println(e);
		}
			return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path="/")
	public ResponseEntity<?> read() {
		List<StaffsFamily> list=staffsFamilyRepo.findAll();
		
		ResponseEntity<?> returntype = crudReturnService.controllerReturnForList(list);
		return returntype;
	}
	
	@GetMapping(path="/findByStaff/{code}")
	public ResponseEntity<?> findByStaff(@PathVariable String code) {
		List<StaffsFamily> list=staffsFamilyRepo.findByStaffsCode(code);
		
		if(list!=null) {
			if(list.size()>0) {
			return new ResponseEntity<Messages>(HttpResponses.fetched(list), HttpStatus.OK);
			}
			
			else {
				return new ResponseEntity<Messages>(HttpResponses.notfound(),HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity<Messages>(HttpResponses.notfound(),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Messages> delete(@PathVariable int id) {
		boolean stafffamily_present=staffsFamilyRepo.findById(id).isPresent();

		if(stafffamily_present) {
			staffsFamilyRepo.deleteById(id);
			return new ResponseEntity<Messages>(HttpResponses.received(), HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<Messages>(HttpResponses.notfound(),HttpStatus.NOT_FOUND);
		}
	}

}
