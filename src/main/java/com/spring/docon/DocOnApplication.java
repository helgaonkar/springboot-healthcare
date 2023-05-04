package com.spring.docon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableKafka
//@CrossOrigin(originPatterns = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(originPatterns = "*")
public class DocOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocOnApplication.class, args);
	}

}
