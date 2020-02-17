package com.peepalsoft.app.message;

import java.util.Arrays;
import java.util.List;

public class HttpResponses {
	
	public static  Messages created(Object object) {
		Messages m=new Messages();
		m.setStatus(true);
		m.setStatusCode("201");
		m.setMessage("Successful creation of a resource.");
		m.setDatas(object);
		return m;
	}
	
	public static  Messages badrequest() {
		Messages m=new Messages();
		m.setStatus(false);
		m.setStatusCode("400");
		m.setMessage("Malformed syntax or a bad query.");
		return m;
	}
	
	public static  Messages passwordMismatch() {
		Messages m=new Messages();
		m.setStatus(false);
		m.setStatusCode("");
		m.setMessage("Passwords Not Matched.");
		return m;
	}
	
	public static  Messages invalidPassword() {
		Messages m=new Messages();
		m.setStatus(false);
		m.setStatusCode("");
		m.setMessage("Invalid Password.");
		return m;
	}
	
	public static  Messages fetched(Object object) {
		Messages m = new Messages();
		m.setStatus(true);
		m.setStatusCode("200");
		m.setDatas(object);
		m.setMessage("No error, operation successful.");
		return m;
	}
	
	public static  Messages notfound() {
		Messages m = new Messages();
		m.setStatus(false);
		m.setStatusCode("404");
		m.setMessage("Resource not found.");
		return m;
	}
	
	public static  Messages received() {
		Messages m = new Messages();
		m.setStatus(true);
		m.setStatusCode("202");
		m.setMessage("The request was received.");
		return m;
	}
	public static  Messages validationerror(List<String> errors) {
		List<Object> objectList = Arrays.asList(errors.toArray());
		Messages m = new Messages();
		m.setStatus(false);
		m.setStatusCode("422");
		m.setMessage("Unsupported Entities");
		m.setDatas(objectList);		
		return m;
	}
}
