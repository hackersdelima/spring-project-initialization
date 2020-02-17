package com.peepalsoft.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.peepalsoft.app.component.CrudReturnService;
import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.exception.ProductNotfoundException;
import com.peepalsoft.app.exception.ValidationErrorException;
import com.peepalsoft.app.message.HttpResponses;
import com.peepalsoft.app.message.Messages;
import com.peepalsoft.app.repo.OfficeRepo;

@RestController
@RequestMapping("office")
public class OfficeController {

	@Autowired
	OfficeRepo officeRepo;

	@Autowired
	CrudReturnService<Office> crudReturnService;
	
	// UI connection
	@GetMapping(path = "/create-page")
	public ModelAndView createpage() {
		ModelAndView model = new ModelAndView("office/create");
		model.addObject("pagetitle", "OFFICE");
		return model;
	}

	@GetMapping(path = "/view-page")
	public ModelAndView viewpage() {
		ModelAndView model = new ModelAndView("office/view");
		model.addObject("pagetitle", "OFFICE");
		return model;
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> read() {
		List<Office> list = officeRepo.findAll();

		ResponseEntity<?> returntype = crudReturnService.controllerReturnForList(list);
		return returntype;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Messages> read(@PathVariable int id) {
		try {
			Office staffsOffice = officeRepo.findById(id).get();
			if (staffsOffice != null) {
				return new ResponseEntity<Messages>(HttpResponses.fetched(staffsOffice), HttpStatus.OK);
			}
		} catch (Exception e) {
		}
		return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);

	}

	@PostMapping
	public ResponseEntity<Messages> create(@Validated @RequestBody Office staffsOffice, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationErrorException(bindingResult);
		} else {
			Office savedOffice = officeRepo.save(staffsOffice);

			if (savedOffice != null) {
				return new ResponseEntity<Messages>(HttpResponses.created(savedOffice), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Messages> delete(@PathVariable int id) {
		try {
			if (id > 1) {
				Office staffsOffice = officeRepo.findById(id).get();
				if (staffsOffice == null) {
					throw new ProductNotfoundException();
				} else {
					officeRepo.deleteById(id);
					
					return new ResponseEntity<Messages>(HttpResponses.received(), HttpStatus.ACCEPTED);
				}
			}

		} catch (Exception e) {
		}
		throw new ProductNotfoundException();

	}

}
