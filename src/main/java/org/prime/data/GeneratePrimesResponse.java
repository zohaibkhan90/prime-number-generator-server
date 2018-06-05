package org.prime.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zohaib
 *
 */
public class GeneratePrimesResponse {
	private String userName;
	
	private List<Integer> primeNumbers;
	
	private String message;
	
	private String algorithm;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Integer> getPrimeNumbers() {
		if(null == primeNumbers)
			primeNumbers = new ArrayList<Integer>();
		return primeNumbers;
	}

	public void setPrimeNumbers(List<Integer> primeNumbers) {
		this.primeNumbers = primeNumbers;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	
}
