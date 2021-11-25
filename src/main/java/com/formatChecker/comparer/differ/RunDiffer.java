package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.Run;

public class RunDiffer implements Differ {
    Run<Boolean, Double> actualRun;
    Run<Boolean, Double> expectedRun;
    Run<String, String> runDifference;

    public RunDiffer(Run<Boolean, Double> actualRun, Run<Boolean, Double> expectedRun) {
        this.actualRun = actualRun;
        this.expectedRun = expectedRun;
        this.runDifference = getDifference();
    }

    Run<String, String> getDifference() {
        Run<String, String> runDifference = new Run<>();

        runDifference.setFontFamily(checkStringParameter(
                actualRun.getFontFamily(),
                expectedRun.getFontFamily(),
                "font family"));

        runDifference.setFontSize(checkDoubleParameter(
                actualRun.getFontSize(),
                expectedRun.getFontSize(),
                "font size", "pt"));

        runDifference.setBold(checkBooleanParameter(
                actualRun.getBold(),
                expectedRun.getBold(),
                "bold text"));

        runDifference.setItalic(checkBooleanParameter(
                actualRun.getItalic(),
                expectedRun.getItalic(),
                "italic text"));

        runDifference.setStrikethrough(checkBooleanParameter(
                actualRun.getStrikethrough(),
                expectedRun.getStrikethrough(),
                "strikethrough"));

        runDifference.setUnderline(checkStringParameter(
                actualRun.getUnderline(),
                expectedRun.getUnderline(),
                "underline"));

        runDifference.setVertAlign(checkStringParameter(
                actualRun.getVertAlign(),
                expectedRun.getVertAlign(),
                "vertical align"));

        runDifference.setTextColor(checkStringParameter(
                actualRun.getTextColor(),
                expectedRun.getTextColor(),
                "text color"));

        runDifference.setCharacterSpacing(checkDoubleParameter(
                actualRun.getCharacterSpacing(),
                expectedRun.getCharacterSpacing(),
                "character spacing", "pt"
        ));

        return runDifference;
    }

    public Run<String, String> getRunDifference() {
        return runDifference;
    }
}
