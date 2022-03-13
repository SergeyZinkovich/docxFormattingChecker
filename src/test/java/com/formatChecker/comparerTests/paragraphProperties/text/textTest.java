package com.formatChecker.comparerTests.paragraphProperties.text;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.PARAGRAPH_TEXT_CONFIG_PATH;
import static com.formatChecker.parserTests.constants.PathConstants.PARAGRAPH_TEXT_DOCUMENT_PATH;


public class textTest {
    @DisplayName("Checks the result of comparing paragraph text")
    @Test
    public void testCorrectRunsCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(PARAGRAPH_TEXT_CONFIG_PATH, PARAGRAPH_TEXT_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }
}
