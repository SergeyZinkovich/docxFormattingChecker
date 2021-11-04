package com.formatChecker.fixerTests.section;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.fixerTests.constants.PathConstants.*;

public class SectionTest {
    @DisplayName("Checks the result of fixing sections")
    @Test
    public void testFixSections()
            throws Exception {
        new DocumentController(SECTION_CONFIG_PATH, SECTION_DOCUMENT_PATH);

        String difference = new DifferResultCollector(
                new DocumentController(SECTION_CONFIG_PATH, SECTION_FIXED_DOCUMENT_PATH).getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }
}
