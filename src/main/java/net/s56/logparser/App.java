package net.s56.logparser;

import org.apache.commons.cli.*;

/**
 * Hello world!
 */
public class App {
    private static String OPT_START_DATE = "startDate";
    private static String OPT_DURATION = "duration";
    private static String OPT_THRESHOLD = "threshold";

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = buildOptions();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar LogParser.jar [options]", options);
        }
    }

    private static void validateOptions(CommandLine line) {
        if (line.hasOption(OPT_START_DATE)) {

        }
    }

    private static Options buildOptions() {
        Options options = new Options();
        options.addOption(
                Option.builder()
                        .argName(OPT_START_DATE)
                        .desc("start date for search")
                        .longOpt(OPT_START_DATE)
                        .hasArg()
                        .build()
        );

        options.addOption(
                Option.builder()
                        .argName(OPT_DURATION)
                        .desc("duration of search")
                        .longOpt(OPT_DURATION)
                        .hasArg()
                        .build()
        );


        options.addOption(
                Option.builder()
                        .argName(OPT_THRESHOLD)
                        .desc("threshold for search")
                        .longOpt(OPT_THRESHOLD)
                        .hasArg()
                        .build()
        );

        return options;
    }

}
