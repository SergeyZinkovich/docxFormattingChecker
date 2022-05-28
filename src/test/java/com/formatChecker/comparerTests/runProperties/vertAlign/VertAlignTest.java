package com.formatChecker.comparerTests.runProperties.vertAlign;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.parserTests.constants.PathConstants.RUN_VERT_ALIGN_CONFIG_PATH;
import static com.formatChecker.parserTests.constants.PathConstants.RUN_VERT_ALIGN_DOCUMENT_PATH;

public class VertAlignTest {
    @DisplayName("Checks the result of comparing vertical align")
    @Test
    public void testCompareParagraphsByIndexes()
            throws Exception {

        String difference = new DifferResultCollector(
                new DocumentController(
                        RUN_VERT_ALIGN_CONFIG_PATH,
                        RUN_VERT_ALIGN_DOCUMENT_PATH)
                        .getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }
}
