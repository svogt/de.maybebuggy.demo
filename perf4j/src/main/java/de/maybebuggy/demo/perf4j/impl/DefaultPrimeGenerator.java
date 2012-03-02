package de.maybebuggy.demo.perf4j.impl;

import java.math.BigInteger;

import de.maybebuggy.demo.perf4j.PrimeGenerator;


public class DefaultPrimeGenerator
    implements PrimeGenerator
{
    public BigInteger getNextPrime(BigInteger currentPrime) {
        return currentPrime.nextProbablePrime();
    }
}



