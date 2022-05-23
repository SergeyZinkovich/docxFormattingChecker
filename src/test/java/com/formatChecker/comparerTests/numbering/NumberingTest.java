package com.formatChecker.comparerTests.numbering;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;
import static com.formatChecker.parserTests.constants.PathConstants.EXISTENCE_RUN_INCORRECT_DOCUMENT_PATH;

public class NumberingTest {
    @DisplayName("Checks numbering, when it is correct")
    @Test
    public void testCorrectNumbering() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(NUMBERING_CORRECT_CONFIG_PATH, NUMBERING_CORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks numbering, when it isn't correct")
    @Test
    public void testIncorrectNumbering() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(NUMBERING_INCORRECT_CONFIG_PATH, NUMBERING_INCORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
