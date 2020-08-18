package io.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.service.Service1;

@RestController
@CrossOrigin(origins = "*")
public class Controller1 {
	
	@Autowired
	Service1 service;
	
	Logger logger = LoggerFactory.getLogger(Controller1.class);
	
	@GetMapping(path="/hello1")
	public ResponseEntity<String> helloMessage () {
		logger.info("In Service 1 Controller method");
		String returnMessage = "Hello!! "+service.getMessage() + "<br>" + service.getService2Message();
		
		return ResponseEntity.ok(returnMessage);
	}

}
