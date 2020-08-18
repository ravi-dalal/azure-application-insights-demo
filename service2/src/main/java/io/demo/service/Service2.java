package io.demo.service;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service2 {
	
	Logger logger = LoggerFactory.getLogger(Service2.class);
	
	@Autowired
	private HttpServletRequest request;
	
	public String getMessage () {
		logger.info("In Service 2 method");
		Iterator<String> headersIter = request.getHeaderNames().asIterator();
		while (headersIter.hasNext()) {
			String header = headersIter.next();
			logger.info("Header: {}, Value: {}", header, request.getHeader(header));
		}
		
		return "This is Service 2!";
	}

}
