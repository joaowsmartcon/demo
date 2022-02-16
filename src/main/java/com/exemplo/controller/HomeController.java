package com.exemplo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exemplo.config.Tenant;
import com.exemplo.facade.TesteFacade;

@Controller
public class HomeController {
	
	@Autowired
	TesteFacade testeFacade;

	
	@RequestMapping("/")
	public @ResponseBody String teste() {
		System.out.println("--------------------------------------------------");
		System.out.println("TESTE");
		System.out.println("--------------------------------------------------");
		return "Hello, World";
	}
	
	@RequestMapping(value = "/teste/{id}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> testeTenant(@PathVariable("id") long id) {
		try {
			Tenant.setIdentificador("esc9998");
			return new ResponseEntity<>(testeFacade.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}