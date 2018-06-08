package org.prime.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.prime.data.Execution;
import org.prime.data.GeneratePrimesResponse;
import org.prime.generator.PrimeGeneratorBLImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@ComponentScan({ "org.prime" })
public class Services {

	@Autowired
	PrimeGeneratorBLImpl primeGenerator;
	
	final static Logger logger = Logger.getLogger(Services.class);
	/**
	 * @param lowerRange - lower range (inclusive) for generating the prime numbers
	 * @param upperRange - upper range (inclusive) for generating the prime numbers
	 * @param userName - user's name who is consuming the API
	 * @param algorithm  - algorithm that user picks to generate the prime numbers
	 * @return List of Integers, containing prime numbers in between the range provided in parameters
	 */
	@RequestMapping(value = "generatePrimes", method = RequestMethod.GET)
	GeneratePrimesResponse generatePrimeNumbers(@RequestParam(value="lowerRange")Integer lowerRange, @RequestParam(value="upperRange")Integer upperRange, @RequestParam(value="userName", defaultValue = "Alien")String userName, @RequestParam(value="algorithm", defaultValue = "Simple")String algorithm){
		GeneratePrimesResponse response = new GeneratePrimesResponse();
		try{
			response.setAlgorithm(algorithm);
			response.setUserName(userName);
			if(null == upperRange || null == lowerRange || Math.abs(lowerRange)>Math.abs(upperRange)){
				response.setMessage("Please provide a valid range");
				return response;
			}
			if(null == algorithm || algorithm.length()<1){
				response.setMessage("Please choose an algorithm from one of the followings: Simple, Complex, Parallel");
				return response;
			}
			if(!(algorithm.equalsIgnoreCase("Simple") || algorithm.equalsIgnoreCase("Complex") || algorithm.equalsIgnoreCase("Parallel"))){
				response.setMessage("Implementation for Algorithm ("+algorithm+") not available");
			}
			else {
				response.setPrimeNumbers(primeGenerator.generatePrimes(lowerRange, upperRange, userName, algorithm));
				response.setMessage("Prime numbers generated successfully");
			}
				
			return response;
		}catch(Exception ex){
			logger.error("Problem occured in generatePrimeNumbers API!", ex);
			response.setUserName(userName);
			response.setAlgorithm(algorithm);
			response.setMessage(ex.getMessage());
			return response;
		}

	}
	
	/**
	 * @return all the previous executions
	 */
	@RequestMapping(value = "getExecutions", method = RequestMethod.GET)
	List<Execution> getExecutions(){
		try {
			return primeGenerator.getAllExecutions();
		} catch(Exception ex) {
			logger.info("Problem occured in getExecutions API!", ex);
			return new ArrayList<Execution>();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Services.class, args);
	}
}
