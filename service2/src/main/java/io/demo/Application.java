package io.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application 
{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
}



/*
 * 
 * 
curl --location --request GET 'http://localhost:9090/api/messages' 
--header 'X-B3-TraceId: 2a53d9df3025c35d' \
--header 'X-B3-SpanId: 2a53d9df3025c35d'
*
*
*/
