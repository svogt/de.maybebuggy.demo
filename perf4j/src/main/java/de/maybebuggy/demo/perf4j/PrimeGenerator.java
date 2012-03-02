package de.maybebuggy.demo.perf4j;

import java.math.BigInteger;


public interface PrimeGenerator
{
    public BigInteger getNextPrime(BigInteger currentPrime);
}
