package de.maybebuggy.demo.perf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.perf4j.aop.Profiled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.maybebuggy.demo.perf4j.impl.DefaultQuoteService;
import de.maybebuggy.demo.perf4j.impl.DelayedQuoteService;
import de.maybebuggy.demo.perf4j.impl.FailingQuoteService;


public class App
{
    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static List<QuoteService> quoteServices = new ArrayList<QuoteService>();

    public static void main(String[] args)
    {
        log.info("Starting App...");

        quoteServices.add(new DefaultQuoteService());
        log.debug("Added default service");
        quoteServices.add(new DelayedQuoteService());
        quoteServices.add(new FailingQuoteService());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            for(int i=0; i<20; i++)
                printQuotes();

            if(checkStop(reader))
                break;
        }

        log.info("Shutting down App...");
    }

    private static boolean checkStop(BufferedReader reader)
    {
        try {
            System.out.println("Press q to quit: ");
            String cmd = reader.readLine();
            if("q".equalsIgnoreCase(cmd) || "quit".equalsIgnoreCase(cmd) || "exit".equalsIgnoreCase(cmd))
                return true;
        } catch (IOException e) {
            return true;
        }
        return false;
    }

    private static void printQuotes()
    {
        final String msg = "Another round of quotes";
        log.info(msg);
        System.out.println(msg);
        for(QuoteService service : quoteServices) {
            try {
                printQuote1(service);
            } catch (Exception e) {
                log.warn("QuoteService <{}> failed...", service.getName());
            }
        }
    }

    @Profiled(tag="printQuote")
    private static void printQuote1(QuoteService quoteService)
    {
        System.out.println(quoteService.getQuote());
    }

    @Profiled(tag="${0.name}")
    private static void printQuote2(QuoteService quoteService)
    {
        System.out.println(quoteService.getQuote());
    }

    @Profiled(tag="{$0.name}", logFailuresSeparately=true)
    private static void printQuote3(QuoteService quoteService)
    {
        System.out.println(quoteService.getQuote());
    }
}
