package org.test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.prime.dao.ExecutionDao;
import org.prime.dao.ExecutionDaoImpl;
import org.prime.data.Execution;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;

public class TestExecutionDao {
	
	private EmbeddedDatabase db;

    ExecutionDao executionDao;
    
    @Before
	public void setUp() throws Exception {
    	System.out.println("Starting test");
    	try{
    		db = new EmbeddedDatabaseBuilder()
    				.setType(EmbeddedDatabaseType.HSQL)
    				.addScript("create.sql")
    				.addScript("insert.sql")
    				.build();    		
    	} catch(Exception ex){
    		ex.printStackTrace();
    	}
	}

	@Test
	public void testSave() {
		try{
    		System.out.println("Saving record");
        	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
        	ExecutionDaoImpl executionDao = new ExecutionDaoImpl();
        	executionDao.setNamedParameterJdbcTemplate(template);
        	Execution execution = new Execution();
        	execution.setUserName("Khan");
        	execution.setTimestamp(Timestamp.from(Instant.now()));
        	execution.setUpperRange(12);
        	execution.setLowerRange(2);
        	execution.setTimeElapsed("3 secs");
        	execution.setAlgorithm("simple");
        	execution.setPrimes(3);
        	System.out.println("Calling execution dao");
        	System.out.println(new Gson().toJson(execution));
        	boolean result = executionDao.save(execution);
        	System.out.println("Saved: "+result);
        	List<Execution> executionsList = executionDao.findExecutionsByName("Khan");
        	Assert.assertNotNull(executionsList);
        	Assert.assertEquals(true, executionsList.size()>0);
        	Assert.assertEquals("Khan", executionsList.get(0).getUserName());
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
	}
	
	@Test
	public void testFindRecordsByName() {
		try{
    		System.out.println("Saving record");
        	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
        	ExecutionDaoImpl executionDao = new ExecutionDaoImpl();
        	executionDao.setNamedParameterJdbcTemplate(template);
        	Execution execution = new Execution();
        	execution.setUserName("zohaib");
        	execution.setTimestamp(Timestamp.from(Instant.now()));
        	execution.setUpperRange(12);
        	execution.setLowerRange(2);
        	execution.setTimeElapsed("33 secs");
        	execution.setAlgorithm("advance");
        	execution.setPrimes(3);
        	System.out.println("Calling execution dao");
        	System.out.println(new Gson().toJson(execution));
        	boolean result = executionDao.save(execution);
        	System.out.println("Saved: "+result);
        	List<Execution> executionAfter = executionDao.findExecutionsByName("zohaib");
        	System.out.println("List for zohaib: "+new Gson().toJson(executionAfter));
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
	}
	
	
	@After
	public void tearDownAfterClass() throws Exception {
		db.shutdown();
	}
    
}
