package org.prime.data;

import java.sql.Timestamp;

/**
 * @author zohaib
 *
 */
public class Execution {
	
	public Execution(){
		
	}
	
	public Execution(String userName, Timestamp timestamp, Integer lowerRange, Integer upperRange, String timeElapsed, String algorithm, Integer primes){
		this.userName = userName;
		this.timestamp = timestamp;
		this.lowerRange = lowerRange;
		this.upperRange = upperRange;
		this.timeElapsed = timeElapsed;
		this.algorithm = algorithm;
		this.primes = primes;
	}
	private String userName;
	
	private Timestamp timestamp;
	
	private Integer lowerRange;
	
	private Integer upperRange;
	
	private String timeElapsed;
	
	private String algorithm;
	
	private Integer primes;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getLowerRange() {
		return lowerRange;
	}

	public void setLowerRange(Integer lowerRange) {
		this.lowerRange = lowerRange;
	}

	public Integer getUpperRange() {
		return upperRange;
	}

	public void setUpperRange(Integer upperRange) {
		this.upperRange = upperRange;
	}

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public Integer getPrimes() {
		return primes;
	}

	public void setPrimes(Integer primes) {
		this.primes = primes;
	}
	
}
