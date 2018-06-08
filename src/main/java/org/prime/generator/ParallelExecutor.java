package org.prime.generator;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zohaib
 * Implementer of Runnable interface, to be used by parallel algorithm of generating prime numbers
 */
public class ParallelExecutor implements Runnable{
	
	private String algorithm;
	
	private Integer lowerRange;
	
	private Integer upperRange;
	
	private List<Integer> primeNumbers;
	
	/**
	 * @param lowerRange
	 * @param upperRange
	 * @param algorithm
	 */
	public ParallelExecutor(Integer lowerRange, Integer upperRange, String algorithm) {
		super();
		this.lowerRange = lowerRange;
		this.upperRange = upperRange;
		this.algorithm = algorithm;
		this.primeNumbers = new ArrayList<Integer>();
	}
	
	@Override
	public String toString() {
		return "ParallelExecutor";
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
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

	public List<Integer> getPrimeNumbers() {
		if (null == primeNumbers)
			primeNumbers = new ArrayList<Integer>();
		return primeNumbers;
	}
	
	/**
	 * the abstract method from Runnable interface, to be used by threads to execute their tasks. 
	 */
	@Override
	public void run() {
		PrimeNumberGenerator primeGenerator = new PrimeNumberGenerator();
		if(algorithm.equalsIgnoreCase("Simple"))
			this.primeNumbers = primeGenerator.generatePrimeNumbersSimple(this.lowerRange, this.upperRange);
		else if(algorithm.equalsIgnoreCase("Complex"))
			this.primeNumbers = primeGenerator.generatePrimeNumbersComplex(this.lowerRange, this.upperRange);
		
	}

}