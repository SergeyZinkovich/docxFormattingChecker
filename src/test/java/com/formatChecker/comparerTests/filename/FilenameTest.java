package com.formatChecker.comparerTests.filename;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;

public class FilenameTest {
    @DisplayName("Checks the filename, when it's correct")
    @Test
    public void testCorrectFilename() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(FILENAME_CORRECT_CONFIG_PATH, FILENAME_CORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks the filename, when it's incorrect")
    @Test
    public void testIncorrectFilename() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(FILENAME_INCORRECT_CONFIG_PATH, FILENAME_INCORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
