package com.formatChecker.comparerTests.existence.paragraph;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;

public class ParagraphExistenceTest {
    @DisplayName("Checks the paragraph existence, when it exists")
    @Test
    public void testExistingParagraph() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(EXISTENCE_PARAGRAPH_CORRECT_CONFIG_PATH, EXISTENCE_PARAGRAPH_CORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks the paragraph existence, when it isn't exist")
    @Test
    public void testNonexistentParagraph() throws Exception {
        String difference = new DifferResultCollector(
                new DocumentController(EXISTENCE_PARAGRAPH_INCORRECT_CONFIG_PATH, EXISTENCE_PARAGRAPH_INCORRECT_DOCUMENT_PATH).getDifference()
        ).collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
