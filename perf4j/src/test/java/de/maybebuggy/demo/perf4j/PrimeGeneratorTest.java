package de.maybebuggy.demo.perf4j;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import de.maybebuggy.demo.perf4j.PrimeGenerator;
import de.maybebuggy.demo.perf4j.impl.DefaultPrimeGenerator;



public class PrimeGeneratorTest
{
    private PrimeGenerator gen = null;

    @Before
    public void setUp()
    {
        gen = new DefaultPrimeGenerator();
    }

    @After
    public void tearDown()
    {
        gen = null;
    }

    @Test
    public void testNextPrime()
    {
        assertEquals(BigInteger.valueOf(11l), gen.getNextPrime(BigInteger.valueOf(7l)));
    }

    @Test
    public void testBigPrime()
    {
        assertEquals(BigInteger.valueOf(22801763489l), gen.getNextPrime(BigInteger.valueOf(22801763488l)));
    }
}
