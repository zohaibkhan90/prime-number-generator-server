package org.prime.generator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.prime.config.SpringRootConfig;
import org.prime.dao.ExecutionDaoImpl;
import org.prime.data.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

/**
 * @author zohaib
 *
 */
@Repository
public class PrimeGeneratorBLImpl implements PrimeGeneratorBL {
	
	final static Logger logger = Logger.getLogger(PrimeGeneratorBLImpl.class);
	
	@Autowired
	SpringRootConfig applicationConfig;

	@Autowired
	ExecutionDaoImpl executionDao;
	
	@Autowired
	PrimeNumberGenerator primeNumberGenerator;
	
	/* (non-Javadoc)
	 * @see org.prime.generator.PrimeGeneratorBL#generatePrimes(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Integer> generatePrimes(Integer lowerRange, Integer upperRange, String userName, String algorithm) {
		List<Integer> primeNumbers = new ArrayList<Integer>();
		try{
			//get the absolute value for range to remove the complexity of negative numbers
			lowerRange = Math.abs(lowerRange);
			upperRange = Math.abs(upperRange);
			//note the current time in milliseconds, before the execution of algorithm
			long timeBefore = System.currentTimeMillis();
			primeNumbers = primeNumberGenerator.generatePrimeNumbersSimple(lowerRange, upperRange);
			//note the current time in milliseconds, after the execution of algorithm
			long timeAfter = System.currentTimeMillis();
			//calculate number of seconds by dividing the difference by 1000
			long executionTime = (timeAfter - timeBefore)/1000;
			
			//set namedParameterJdbcTemplate(the dataSource provider) for executionDao
	    	executionDao.setNamedParameterJdbcTemplate(applicationConfig.getNamedParameterJdbcTemplate());
	    	//Create a new instance of Execution Pojo Class, will be used to insert the new record for execution
	    	Execution execution = new Execution();
	    	execution.setUserName(userName);
	    	execution.setTimestamp(Timestamp.from(Instant.now()));
	    	execution.setUpperRange(upperRange);
	    	execution.setLowerRange(lowerRange);
	    	execution.setTimeElapsed(executionTime+" seconds");
	    	execution.setAlgorithm(algorithm);
	    	execution.setPrimes(primeNumbers.size());
	    	logger.info("Calling execution dao to save execution record: "+new Gson().toJson(execution));
	    	// save the execution in database
	    	boolean result = executionDao.save(execution);
	    	logger.info("Saved: "+result);
	    	//return the generated prime numbers list back
	    	return primeNumbers;
		} catch (Exception ex) {
			logger.error("Problem occured while generating prime numbers!",ex);
			//return the empty list
			return primeNumbers;
		}
	}

	/* (non-Javadoc)
	 * @see org.prime.generator.PrimeGeneratorBL#getAllExecutions()
	 */
	@Override
	public List<Execution> getAllExecutions() {
		// TODO Auto-generated method stub
		return executionDao.findAll();
	}

	/* (non-Javadoc)
	 * @see org.prime.generator.PrimeGeneratorBL#getExecutionsByUser(java.lang.String)
	 */
	@Override
	public List<Execution> getExecutionsByUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
