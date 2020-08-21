package io.demo.service;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.demo.configuration.CustomTelemetry;
import io.demo.cosmosdb.dto.DemoContainer;
import io.demo.cosmosdb.repository.DemoRepository;

@Service
public class Service1 {
	
	Logger logger = LoggerFactory.getLogger(Service1.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DemoRepository demoRepository;
	
	@Value("${sample.app.service2}")
	private String service2Url;
	
	@CustomTelemetry(type = "Cosmos DB")
	public String getMessage () {
		logger.info("In Service 1 getMessage() method");
		Iterable<DemoContainer> demoContainer = demoRepository.findAll();
		String message = "";
		for (DemoContainer document : demoContainer) {
			message += document.getValue() + " ";
		}
		return message;
	}
	
	public String getService2Message () {
		Iterator<String> headersIter = request.getHeaderNames().asIterator();
		while (headersIter.hasNext()) {
			String header = headersIter.next();
			logger.info("Header: {}, Value: {}", header, request.getHeader(header));
		}
		RestTemplate restTemplate = new RestTemplate();
		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
		HttpHeaders httpHeader = httpRequest.getHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeader);
		ResponseEntity<String> response = restTemplate.exchange(service2Url, HttpMethod.GET, requestEntity, String.class);
		return response.getBody();
	}

}
