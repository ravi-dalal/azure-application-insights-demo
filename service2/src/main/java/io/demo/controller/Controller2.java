package io.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.service.Service2;

@RestController
@CrossOrigin(origins = "*")
public class Controller2 {
	
	@Autowired
	Service2 service;
	
	Logger logger = LoggerFactory.getLogger(Controller2.class);
	
	@GetMapping(path="/hello2")
	public ResponseEntity<String> helloMessage () {
		logger.info("In Service 2 Controller method");
		return ResponseEntity.ok(service.getMessage());
	}

}
