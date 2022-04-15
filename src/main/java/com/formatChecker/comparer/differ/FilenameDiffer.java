package com.formatChecker.comparer.differ;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilenameDiffer implements Differ {
    String actualFilename;
    String expectedFilename;
    String difference;

    public FilenameDiffer(String filePath, String expectedFilename) {
        this.actualFilename = getFilenameFromPath(filePath);
        this.expectedFilename = expectedFilename;
        this.difference = compareFilename();
    }

    String getFilenameFromPath(String filePath) {
        Path p = Paths.get(filePath);
        String filename = p.getFileName().toString();
        return filename.substring(0, filename.lastIndexOf("."));
    }

    String compareFilename() {
        if (expectedFilename == null) {
            return null;
        }

        return checkTextParameter(actualFilename, expectedFilename, "filename");
    }

    public String getDifference() {
        return difference;
    }
}
