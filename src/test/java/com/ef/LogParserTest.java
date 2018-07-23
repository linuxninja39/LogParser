package com.ef;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

public class LogParserTest {
    static int c = 0;

    @Test
    public void testRun() throws IOException, URISyntaxException {
        LogParser logParser = new LogParser();
        CommandLineArgs args = new CommandLineArgs();
        args.setDuration("daily");
        args.setThreshold(1);
        args.setStartDate(new Date());

        logParser.run(args);
    }
}
