package com.ef;

import com.ef.entities.IpAddressEntity;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Hello world!
 */
public class Parser {
    private static String OPT_START_DATE = "startDate";
    private static String OPT_DURATION = "duration";
    private static String OPT_THRESHOLD = "threshold";

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = buildOptions();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            LogParser logParser = new LogParser();
            CommandLineArgs args1 = parseOptions(line);
            List<?> results = logParser.run(args1);
            for (Object result : results) {
                Object[] row = (Object[]) result;
                IpAddressEntity ip = (IpAddressEntity) row[0];
                int count = (int) (long) row[1];
                System.out.println(ip.getValue() + ", " + count);
            }

            System.exit(0);

        } catch (ParseException | java.text.ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar LogParser.jar [options]", options);
        } catch (IOException | URISyntaxException e) {
            System.err.println("Failed to open log file.  Reason: " + e.getMessage());
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static CommandLineArgs parseOptions(CommandLine line) throws java.text.ParseException, ParseException {
        CommandLineArgs clArgs = new CommandLineArgs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        clArgs.setStartDate(dateFormat.parse(line.getOptionValue(OPT_START_DATE)));
        clArgs.setThreshold(Long.parseLong(line.getOptionValue(OPT_THRESHOLD)));
        if (!line.getOptionValue(OPT_DURATION).matches("^hourly|daily$")) {
            throw new ParseException("threshold MUST be either hourly or daily");
        }
        clArgs.setDuration(line.getOptionValue(OPT_DURATION));
        return clArgs;
    }

    private static Options buildOptions() {
        Options options = new Options();
        options.addOption(
                Option.builder()
                        .argName(OPT_START_DATE)
                        .required()
                        .desc("start date for search, must be format yyyy-MM-dd.HH:mm:ss. Example: 2017-01-01.13:00:00")
                        .longOpt(OPT_START_DATE)
                        .hasArg()
                        .build()
        );

        options.addOption(
                Option.builder()
                        .argName(OPT_DURATION)
                        .required()
                        .desc("duration of search, must be either 'hourly' or 'daily'")
                        .longOpt(OPT_DURATION)
                        .hasArg()
                        .build()
        );


        options.addOption(
                Option.builder()
                        .argName(OPT_THRESHOLD)
                        .required()
                        .desc("threshold for search, must be integer")
                        .longOpt(OPT_THRESHOLD)
                        .hasArg()
                        .build()
        );

        return options;
    }

}
