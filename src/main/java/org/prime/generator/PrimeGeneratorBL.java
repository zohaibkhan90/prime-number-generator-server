package org.prime.generator;

import java.util.List;

import org.prime.data.Execution;
import org.springframework.stereotype.Repository;

//prime generator business logic
/**
 * @author zohaib
 *
 */
@Repository
public interface PrimeGeneratorBL {
	
	/**
	 * @param lowerRange - 
	 * @param upperRange
	 * @param userName
	 * @param algorithm
	 * @return
	 */
	public List<Integer> generatePrimes(Integer lowerRange, Integer upperRange, String userName, String algorithm);
	
	/**
	 * @return all records of previous executions, for prime number generation
	 */
	public List<Execution> getAllExecutions();
	
	/**
	 * @param userName
	 * @return all records of previous executions, performed by user provide in parameter, for prime number generation
	 */
	public List<Execution> getExecutionsByUser(String userName);
	
}
