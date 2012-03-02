package de.maybebuggy.demo.perf4j.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.maybebuggy.demo.perf4j.QuoteService;


public class DefaultQuoteService
    implements QuoteService
{
    List<String> quotes = new ArrayList<String>();
    Random random = new Random();


    public DefaultQuoteService()
    {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/seeburger/dt/expexchange/perf4j/impl/quotes.txt");
        if(in==null) {
            quotes.add("Quote database not found!");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String quote = null;
        try {
            while((quote=reader.readLine())!=null)
                quotes.add(quote);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getQuote()
    {
        return quotes.get(random.nextInt(quotes.size()));
    }

    public String getName()
    {
        return "DefaultQuoteService";
    }

}
