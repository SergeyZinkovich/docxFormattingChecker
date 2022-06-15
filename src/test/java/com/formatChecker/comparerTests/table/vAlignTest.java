package com.formatChecker.comparerTests.table;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.*;

public class vAlignTest {
    @DisplayName("Checks table vAlign, when it is correct")
    @Test
    public void testCorrectRunsCount()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(
                        TABLE_V_ALIGN_CORRECT_CONFIG_PATH,
                        TABLE_V_ALIGN_CORRECT_DOCUMENT_PATH)
                        .getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks table vAlign, when it isn't correct")
    @Test
    public void testCompareParagraphsByHeadings()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(
                        TABLE_V_ALIGN_INCORRECT_CONFIG_PATH,
                        TABLE_V_ALIGN_INCORRECT_DOCUMENT_PATH)
                        .getDifference())
                .collectDifferenceAsString();

        Assertions.assertNotEquals(OK_MESSAGE, difference);
    }
}
