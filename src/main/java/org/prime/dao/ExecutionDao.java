package org.prime.dao;

import java.util.List;

import org.prime.data.Execution;



public interface ExecutionDao {

	
	public List<Execution> findExecutionsByName(String userName);
	
	boolean save(Execution record);
	
	List<Execution> findAll();
	
}
