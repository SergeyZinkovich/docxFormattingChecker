package com.formatChecker.comparerTests.paragraph_properties.runs_count;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;

public class RunsCountTest {
    @DisplayName("Checks the result of comparing runs count, when it's correct")
    @Test
    public void testCorrectRunsCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(RUNS_COUNT_CORRECT_CONFIG_PATH, RUNS_COUNT_CORRECT_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks the result of comparing runs count, when it's incorrect")
    @Test
    public void testIncorrectRunsCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(RUNS_COUNT_INCORRECT_CONFIG_PATH, RUNS_COUNT_INCORRECT_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
