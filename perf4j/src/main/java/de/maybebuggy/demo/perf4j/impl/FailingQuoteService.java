package de.maybebuggy.demo.perf4j.impl;

import java.util.Random;

import de.maybebuggy.demo.perf4j.QuoteService;


public class FailingQuoteService
    implements QuoteService
{
    private QuoteService quoteService = new DelayedQuoteService(2000);
    private Random random = new Random();
    private int failingRate = 30;


    public String getName()
    {
        return "FailingQuoteService";
    }

    public String getQuote()
    {
        String quote = quoteService.getQuote();
        if(failingRate<=random.nextInt(100))
            throw new RuntimeException("Quote service failed");
        else
            return quote;
    }

}
