package org.prime.dao;

import java.util.List;

import org.prime.data.Execution;



public interface ExecutionDao {

	
	/**
	 * @param userName
	 * @return all records of previous executions, performed by user provide in parameter, for prime number generation from database 
	 */
	public List<Execution> findExecutionsByName(String userName);
	
	/**
	 * @param record
	 * @return true of record is saved successfully, false otherwise
	 */
	boolean save(Execution record);
	
	/**
	 * @return all records of previous executions for prime number generation from database
	 */
	List<Execution> findAll();
	
}
