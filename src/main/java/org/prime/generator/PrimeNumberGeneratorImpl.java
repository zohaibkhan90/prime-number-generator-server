package org.prime.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author zohaib
 *
 */
@Repository
public class PrimeNumberGeneratorImpl implements PrimeNumberGenerator {
	
	
	/* (non-Javadoc)
	 * @see org.prime.generator.PrimeNumberGenerator#generatePrimeNumbersSimple(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Integer> generatePrimeNumbersSimple(Integer lowerRange, Integer upperRange) {
		List<Integer> primes = new ArrayList<Integer>();
		//excluding 0 and 1, as by definition, prime numbers are greater than 1
		if(lowerRange<2)
			lowerRange=2;
		//from lower range (inclusive) to upper range (inclusive). add primes in the list.
        for (int prime = lowerRange; prime <= upperRange; prime++) {
            boolean isPrimeNumber = true;

            // check to see if the number is prime
            for (int primeDivisor = 2; primeDivisor < prime; primeDivisor++) {
                if (prime % primeDivisor == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            // if the number is prime, add in the primes list.
            if (isPrimeNumber) {
                primes.add(prime);
            }
        }
		return primes;
	}

	/* (non-Javadoc)
	 * @see org.prime.generator.PrimeNumberGenerator#generatePrimeNumbersComplex(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Integer> generatePrimeNumbersComplex(Integer lowerRange, Integer upperRange) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
