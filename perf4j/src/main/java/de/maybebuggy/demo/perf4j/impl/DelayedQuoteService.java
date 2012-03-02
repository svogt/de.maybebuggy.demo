package de.maybebuggy.demo.perf4j.impl;

import java.util.Random;

import de.maybebuggy.demo.perf4j.QuoteService;


public class DelayedQuoteService
    implements QuoteService
{
    private QuoteService quoteService = new DefaultQuoteService();
    private Random random = new Random();
    private int maxWait = 5000;

    public DelayedQuoteService()
    {
    }
    public DelayedQuoteService(int maxWait)
    {
        this.maxWait = maxWait;
    }

    public String getQuote()
    {
        try {
            Thread.sleep(random.nextInt(maxWait));
        } catch (Exception e) {
            // ignore
        }
        return quoteService.getQuote();
    }

    public String getName()
    {
        return "DelayedQuoteService";
    }

}
