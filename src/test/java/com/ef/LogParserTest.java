package com.ef;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LogParserTest {

    @Test
    public void testRun() throws IOException, URISyntaxException, ParseException {
        LogParser logParser = new LogParser();
        CommandLineArgs args = new CommandLineArgs();
        args.setDuration("daily");
        args.setThreshold(4L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        args.setStartDate(dateFormat.parse("2017-01-01 07:01:07"));

        logParser.run(args);
    }
}
