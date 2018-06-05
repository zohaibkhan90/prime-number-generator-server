package org.prime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.prime.data.Execution;
import java.sql.ResultSet;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zohaib
 * Implementation of Dao Layer for transaction of HSQL - in-memory database
 */
@Repository
public class ExecutionDaoImpl implements ExecutionDao {

	Logger logger = Logger.getLogger(ExecutionDaoImpl.class);
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.prime.dao.ExecutionDao#findExecutionsByName(java.lang.String)
	 */
	@Override
	public List<Execution> findExecutionsByName(String userName) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", userName);
			String sql = "SELECT * FROM execution WHERE username=:username";
	        List<Execution> result = namedParameterJdbcTemplate.query(sql, params, new ExecutionMapper());
	        return result;			
		} catch(Exception ex) {
			logger.error("Problem occured while loading records from database!", ex);
			return new ArrayList<>();
		}

	}
	/* (non-Javadoc)
	 * @see org.prime.dao.ExecutionDao#save(org.prime.data.Execution)
	 */
	@Override
	public boolean save(Execution execution) {
		try {
			String query = "INSERT INTO execution (username, timestamp, upperrange, lowerrange, timeelapsed, algorithm, primes) VALUES (:username,:timestamp,:upperrange,:lowerrange,:timeelapsed,:algorithm,:primes)";
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put("username", execution.getUserName());
			namedParameters.put("timestamp", execution.getTimestamp());
			namedParameters.put("upperrange", execution.getUpperRange());
			namedParameters.put("lowerrange", execution.getLowerRange());
			namedParameters.put("timeelapsed", execution.getTimeElapsed());
			namedParameters.put("algorithm", execution.getAlgorithm());
			namedParameters.put("primes", execution.getPrimes());
			namedParameterJdbcTemplate.update(query, namedParameters);
			return true;
		} catch(Exception ex) {
			logger.error("Problem occured while saving record in database!", ex);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.prime.dao.ExecutionDao#findAll()
	 */
	@Override
	public List<Execution> findAll() {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "SELECT * FROM execution";
			List<Execution> result = namedParameterJdbcTemplate.query(sql, params, new ExecutionMapper());
			return result;
		} catch(Exception ex) {
			logger.error("Problem occured while loading records from database!", ex);
			return new ArrayList<>();
		}
	}

	private static final class ExecutionMapper implements RowMapper<Execution> {

		/**
		 * @param rs
		 * @param rowNum
		 * Implementation of RowMapper interface to map results data from result set into our data container class
		 */
		public Execution mapRow(ResultSet rs, int rowNum) throws SQLException {
			Execution execution = new Execution();
			execution.setUserName(rs.getString("username"));
			execution.setTimestamp(rs.getTimestamp("timestamp"));
			execution.setUpperRange(rs.getInt("upperrange"));
			execution.setLowerRange(rs.getInt("lowerrange"));
			execution.setTimeElapsed(rs.getString("timeelapsed"));
			execution.setAlgorithm(rs.getString("algorithm"));
			execution.setPrimes(rs.getInt("primes"));
			return execution;
		}
	}
}
