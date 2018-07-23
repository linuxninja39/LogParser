package net.s56.logparser;

import org.apache.commons.cli.CommandLine;

public class LogParser {
    private CommandLine line;
    public LogParser() {}

    public LogParser(CommandLine line) {
        this.line = line;
    }

    public String run() {
        return "cool n stuffz";
    }

    public CommandLine getLine() {
        return line;
    }

    public void setLine(CommandLine line) {
        this.line = line;
    }
}
