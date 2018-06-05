package org.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.prime.generator.PrimeNumberGenerator;
import org.prime.generator.PrimeNumberGeneratorImpl;

import com.google.gson.Gson;

public class TestPrimeGenerator {

	final static Logger logger = Logger.getLogger(TestPrimeGenerator.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGeneratePrimeSimple() {
		PrimeNumberGenerator primeGenerator = new PrimeNumberGeneratorImpl();
		List<Integer> primeNumbers = primeGenerator.generatePrimeNumbersSimple(1, 200);
		logger.info(new Gson().toJson(primeNumbers));
		Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199};
		List<Integer> gt = new ArrayList<>(Arrays.asList(primes)); //gt = ground truth, we know these are the prime numbers. 
		if(!gt.containsAll(primeNumbers)){
			logger.info("Not all are primes");
			Assert.fail("Not all are primes");
		}
	}

}
