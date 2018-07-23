package com.ef;

import org.apache.commons.cli.*;

import java.text.SimpleDateFormat;

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
            LogParser logParser = new LogParser(line);
            String output = logParser.run();
            System.out.print(output);
        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar LogParser.jar [options]", options);
        }
    }

    private static CommandLineArgs parseOptions(CommandLine line) throws java.text.ParseException {
        CommandLineArgs clArgs = new CommandLineArgs();
        clArgs.setStartDate(new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse(line.getOptionValue(OPT_START_DATE)));
        return clArgs;
    }

    private static Options buildOptions() {
        Options options = new Options();
        options.addOption(
                Option.builder()
                        .argName(OPT_START_DATE)
                        .required()
                        .desc("start date for search")
                        .longOpt(OPT_START_DATE)
                        .hasArg()
                        .build()
        );

        options.addOption(
                Option.builder()
                        .argName(OPT_DURATION)
                        .required()
                        .desc("duration of search")
                        .longOpt(OPT_DURATION)
                        .hasArg()
                        .build()
        );


        options.addOption(
                Option.builder()
                        .argName(OPT_THRESHOLD)
                        .required()
                        .desc("threshold for search")
                        .longOpt(OPT_THRESHOLD)
                        .hasArg()
                        .build()
        );

        return options;
    }

}
