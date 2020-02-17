package com.peepalsoft.app.exception;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.peepalsoft.app.message.HttpResponses;

@RestControllerAdvice
public class ProductExceptionController extends ResponseEntityExceptionHandler{
	   
	@ExceptionHandler(value = ProductNotfoundException.class)
	public ResponseEntity<Object> productNotFoundException() {
	      return new ResponseEntity<>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ValidationErrorException.class)
	public ResponseEntity<Object> validationException(ValidationErrorException validationException) {
		List<FieldError> errors = validationException.bindingResult.getFieldErrors();
		List<String> errormsg= new ArrayList<String>();
	    for (FieldError error : errors ) {
	    	errormsg.add(error.getField()+" : "+error.getDefaultMessage());
	    }
	      return new ResponseEntity<>(HttpResponses.validationerror(errormsg), HttpStatus.NOT_FOUND);
	}
}
