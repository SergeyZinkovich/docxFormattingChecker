package com.formatChecker.comparerTests.existence.run;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;

public class RunExistenceTest {
    @DisplayName("Checks the run existence, when it exists")
    @Test
    public void testExistingRun() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(EXISTENCE_RUN_CORRECT_CONFIG_PATH, EXISTENCE_RUN_CORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks the run existence, when it isn't exist")
    @Test
    public void testNonexistentRun() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(EXISTENCE_RUN_INCORRECT_CONFIG_PATH, EXISTENCE_RUN_INCORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
