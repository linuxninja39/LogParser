package com.ef;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;

class LogParser {
    private static String FILE_NAME = "access.log";

    String run(CommandLineArgs args) throws IOException, URISyntaxException {
        Path file = findFile();
        System.out.println(file);
        Files.lines(file).forEach(this::handleLine);
        return "handleLine n stuffz";
    }

    private Path findFile() throws URISyntaxException {
        Path sameDir = Paths.get(LogParser.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        Path logFile = Paths.get(sameDir.toString(), FILE_NAME);
        if (sameDir.toString().toLowerCase().endsWith(".jar")) {
            logFile = Paths.get(sameDir.toString(), "../", FILE_NAME);
        }
        return logFile;
    }

    private void handleLine(String line) {
        String[] parts = line.split("\\|");
    }
}
