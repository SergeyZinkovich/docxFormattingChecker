package com.formatChecker.fixerTests.run;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.controller.DocumentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.fixerTests.constants.MessageConstants.OK_MESSAGE;
import static com.formatChecker.fixerTests.constants.PathConstants.*;

public class RunTest {
    @DisplayName("Checks the result of fixing runs by index")
    @Test
    public void testFixRunsByIndex()
            throws Exception {
        new DocumentController(RUN_BY_INDEX_CONFIG_PATH, RUN_BY_INDEX_DOCUMENT_PATH);

        String difference = new DifferResultCollector(
                new DocumentController(RUN_BY_INDEX_CONFIG_PATH, RUN_BY_INDEX_FIXED_DOCUMENT_PATH)
                        .getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }

    @DisplayName("Checks the result of fixing runs by heading")
    @Test
    public void testFixRunsByHeading()
            throws Exception {
        new DocumentController(RUN_BY_HEADING_CONFIG_PATH, RUN_BY_HEADING_DOCUMENT_PATH);

        String difference = new DifferResultCollector(
                new DocumentController(RUN_BY_HEADING_CONFIG_PATH, RUN_BY_HEADING_FIXED_DOCUMENT_PATH)
                        .getDifference())
                .collectDifferenceAsString();

        Assertions.assertEquals(OK_MESSAGE, difference);
    }
}
