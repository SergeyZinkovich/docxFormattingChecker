package com.formatChecker.comparerTests.runProperties.text;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.RUNS_TEXT_CONFIG_PATH;
import static com.formatChecker.parserTests.constants.PathConstants.RUNS_TEXT_DOCUMENT_PATH;


public class RunsTextTest {
    @DisplayName("Checks the result of comparing runs text")
    @Test
    public void testCorrectRunsCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(RUNS_TEXT_CONFIG_PATH, RUNS_TEXT_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }
}
