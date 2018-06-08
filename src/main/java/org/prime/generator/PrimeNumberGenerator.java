package org.prime.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author zohaib
 * Generates Prime Numbers, provided a lower and upper range of numbers.
 * Provided 3 different algorithms, a Simple brute force, a Complex intuitive one, and a parallel processing algorithm, to generate the prime numbers
 */
@Repository
public class PrimeNumberGenerator {
	
	
	/**
	 * set its value to number of threads you want to create in parallel execution algorithm
	 */
	private static final int WORKERS = 4;

	/**
	 * @param lowerRange - the lower range for generating prime numbers
	 * @param upperRange - the upper range for generating prime numbers
	 * @return list of prime numbers with in the range provided, uses simple brute force algorithm
	 */
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
	
	/**
	 * @param lowerRange - the lower range for generating prime numbers
	 * @param upperRange - the upper range for generating prime numbers
	 * @return list of prime numbers with in the range provided, considers range for divisors(intuitively stop checking if a number is prime after a range), excludes numbers that are possible non-primes
	 */
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
            while(primeDivisor * primeDivisor <= prime){ //if divisor's square reaches or exceeds the number and we still didn't find a divisor, that means it is a prime number
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
	
	/**
	 * @param lowerRange - the lower range for generating prime numbers
	 * @param upperRange - the upper range for generating prime numbers
	 * @param algorithm - the algorithm to be used for generating prime numbers. e.g Simple, Complex
	 * @return list of prime numbers. it generates list of prime numbers using multi-threading. if you want to change number of threads, set static final member "WORKERS" to your desired number of threads.
	 */
	public List<Integer> generatePrimeNumbersParallel(Integer lowerRange, Integer upperRange, String algorithm) {
		try{
			
			//find the total range of initial input range (upper and lower range inclusive)
			Integer totalRange = (upperRange - lowerRange) + 2;
			if(totalRange<15) //for a low range, avoid creating multiple threads to avoid context switching overhead
				return this.generatePrimeNumbersSimple(lowerRange, upperRange);

			//Create threads array
			ParallelExecutor[] executors = new ParallelExecutor[WORKERS];
			Thread[] threads = new Thread[WORKERS];
			
			//divide the total range equally between all threads
			for(int i=0; i<WORKERS; i++){
				executors[i] = new ParallelExecutor(lowerRange+(totalRange / WORKERS * i), (lowerRange+(totalRange / WORKERS * (i + 1))), algorithm);
				threads[i] = new Thread(executors[i]);
			}
			//take special care of first thread, so that we don't go below the lowerRange provided.
			executors[0] = new ParallelExecutor(lowerRange, (lowerRange+(totalRange/WORKERS)), algorithm);
			threads[0] = new Thread(executors[0]);
			//take special care of last thread, so that we don't go above the upperRange provided.
			executors[WORKERS-1] = new ParallelExecutor(upperRange-(totalRange/WORKERS), upperRange, algorithm);
			threads[WORKERS-1] = new Thread(executors[WORKERS-1]);
			
			//start all the threads
			for (int i = 0; i < WORKERS; i++) {
				threads[i].start();
			}
			
			//join all the threads, so that we get individual results from all of them before we can proceed to merge them 
			for (int i = 0; i < WORKERS; i++) {
				threads[i].join();
			}
			
			List<Integer> primeNumbers = new ArrayList<Integer>();
			Integer exclude = 0; //use for the common primes between multiple threads. e.g result from thread-1 = [2, 3, 5, 7] and result from thread-2 = [7, 11, 13]. so we need to include 7 only once while merging
			for (int i = 0; i < WORKERS; i++) {
				if(executors[i].getPrimeNumbers().get(0).equals(exclude)){ //if the first element from a thread is the same as the last element from the previous thread
					executors[i].getPrimeNumbers().remove(0); //remove this common element
				}
				if(executors[i].getPrimeNumbers().size()>0)
					exclude = executors[i].getPrimeNumbers().get(executors[i].getPrimeNumbers().size()-1); //set exclude to the last element of this thread so that same logic can be repeated for the next one
				//add results from this thread to the main list of prime numbers
				primeNumbers.addAll(executors[i].getPrimeNumbers());				
			}
			return primeNumbers;
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Integer>();
		}
	}

}
