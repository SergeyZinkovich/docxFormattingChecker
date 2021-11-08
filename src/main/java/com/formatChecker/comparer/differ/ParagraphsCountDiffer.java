package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.ParagraphsCount;

public class ParagraphsCountDiffer {
    Integer actualParagraphs;
    ParagraphsCount expectedParagraphsCount;
    String difference;

    public ParagraphsCountDiffer(Integer actualParagraphs, ParagraphsCount expectedParagraphsCount) {
        this.actualParagraphs = actualParagraphs;
        this.expectedParagraphsCount = expectedParagraphsCount;
        this.difference = compareParagraphsCount();
    }

    String compareParagraphsCount() {
        if (actualParagraphs == null)
            return "Could not read number of paragraphs";
        if (expectedParagraphsCount == null)
            return null;
        if (actualParagraphs >= expectedParagraphsCount.getMin() && actualParagraphs <= expectedParagraphsCount.getMax()) {
            return null;
        } else {
            return String.format("document has %s paragraphs, should have %d-%d paragraphs",
                    actualParagraphs, expectedParagraphsCount.getMin(), expectedParagraphsCount.getMax());
        }
    }

    public String getDifference() {
        return difference;
    }
}
