package org.prime.generator;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author zohaib
 *
 */
@Repository
public interface PrimeNumberGenerator {
	
	/**
	 * @param lowerRange
	 * @param upperRange
	 * @return list of prime numbers with in the range provided, uses simple brute force algorithm
	 */
	List<Integer> generatePrimeNumbersSimple(Integer lowerRange, Integer upperRange);
	
	
	/**
	 * @param lowerRange
	 * @param upperRange
	 * @return list of prime numbers with in the range provided, considers range for divisors(intuitively stop checking if a number is prime after a range), excludes numbers that are possible non-primes
	 */
	List<Integer> generatePrimeNumbersComplex(Integer lowerRange, Integer upperRange);
	
	
}
