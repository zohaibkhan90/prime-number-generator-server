package org.prime.controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Services {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Services.class, args);
	}
}
