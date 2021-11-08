package com.formatChecker.comparerTests.paragraphsCount;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;

public class ParagraphsCountTest {
    @DisplayName("Checks the result of comparing paragraph count, when it's correct")
    @Test
    public void testCorrectParagraphCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(PARAGRAPH_COUNT_CORRECT_CONFIG_PATH, PARAGRAPH_COUNT_CORRECT_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks the result of comparing paragraph count, when it's incorrect")
    @Test
    public void testIncorrectParagraphCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(PARAGRAPH_COUNT_INCORRECT_CONFIG_PATH, PARAGRAPH_COUNT_INCORRECT_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
