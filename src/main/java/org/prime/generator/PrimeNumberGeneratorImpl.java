package org.prime.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author zohaib
 *
 */
@Repository
public class PrimeNumberGeneratorImpl implements PrimeNumberGenerator {
	
	Logger logger = Logger.getLogger(PrimeNumberGeneratorImpl.class);
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
		List<Integer> primes = new ArrayList<Integer>();
		//excluding 0 and 1, as by definition, prime numbers are greater than 1
		if(lowerRange<3){ //selecting 2 by ourselves if it lies with in the range.
			lowerRange=3; //we will neglect even numbers in computation below, because even numbers are not prime. except 2.
			primes.add(2);//so we add 2 as an exception separately
		}

		//from lower range (inclusive) to upper range (inclusive). add primes in the list.
        for (int prime = lowerRange; prime <= upperRange; prime++) {
        	//skip the even numbers, as they are not prime for sure.
        	if(prime%2==0)
        		continue; 
        		
            boolean isPrimeNumber = true;
            int primeDivisor = 2;
            // check to see if the number is prime
            while(primeDivisor * primeDivisor <= prime){
            	if (prime % primeDivisor == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            	primeDivisor++;
            }
            // if the number is prime, add in the primes list.
            if (isPrimeNumber) {
                primes.add(prime);
            }
        }
		return primes;
	}
	
	
	
}
