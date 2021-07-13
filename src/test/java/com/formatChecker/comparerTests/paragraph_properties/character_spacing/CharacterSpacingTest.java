package com.formatChecker.comparerTests.paragraph_properties.character_spacing;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.PARAGRAPH_CHARACTER_SPACING_CONFIG_PATH;
import static com.formatChecker.parserTests.constants.PathConstants.PARAGRAPH_CHARACTER_SPACING_DOCUMENT_PATH;


public class CharacterSpacingTest {
    @DisplayName("Checks the result of comparing character spacing")
    @Test
    public void testCompareParagraphsByIndexes()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(
                        PARAGRAPH_CHARACTER_SPACING_CONFIG_PATH,
                        PARAGRAPH_CHARACTER_SPACING_DOCUMENT_PATH)
                        .getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }
}
