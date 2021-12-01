package com.formatChecker.controller;

import com.formatChecker.comparer.differ.RunDiffer;
import com.formatChecker.config.model.Config;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.model.participants.Run;
import com.formatChecker.config.model.participants.Style;
import com.formatChecker.fixer.RunFixer;
import org.docx4j.wml.R;

import java.util.Map;

public class RunController {
    private static final String HEADING_STYLE_NAME = "heading";
    private static final String BODY_STYLE_NAME = "body";

    Integer paragraphIndex;
    Integer runIndex;
    R documentRun;
    Run<Boolean, Double> actualRun;
    Style expectedStyle;
    Run<Boolean, Double> expectedRun;
    Boolean shouldFix;

    Paragraph<String, String> differenceParagraph;
    Map<Integer, String> configStyles;
    Config config;
    Integer headingLevel;

    public RunController(Integer paragraphIndex,
                         Integer runIndex,
                         R documentRun,
                         Run<Boolean, Double> actualRun,
                         Paragraph<String, String> differenceParagraph,
                         Map<Integer, String> configStyles,
                         Integer headingLevel,
                         Config config,
                         Boolean shouldFix) {
        this.paragraphIndex = paragraphIndex;
        this.runIndex = runIndex;
        this.documentRun = documentRun;
        this.actualRun = actualRun;
        this.shouldFix = shouldFix;

        this.differenceParagraph = differenceParagraph;
        this.config = config;
        this.configStyles = configStyles;
        this.headingLevel = headingLevel;
    }

    public void parseRun() {
        if (configStyles == null) {
            if (headingLevel > 0) {
                expectedStyle = config.getStyles().get(HEADING_STYLE_NAME + headingLevel);
            } else {
                expectedStyle = config.getStyles().get(BODY_STYLE_NAME);
            }
        } else {
            expectedStyle = config.getStyles().get(configStyles.get(paragraphIndex));
        }

        if (expectedStyle.getParagraph().getRuns() != null && expectedStyle.getParagraph().getRuns().size() > 0) {
            if (expectedStyle.getParagraph().getRuns().size() > runIndex) {
                expectedRun = expectedStyle.getParagraph().getRuns().get(runIndex);
            } else {
                expectedRun = expectedStyle.getParagraph().getRuns().get(expectedStyle.getParagraph().getRuns().size()-1);
            }

            compareRun();
        }
    }

    void compareRun() {
        Run<String, String> differenceRun = new RunDiffer(actualRun, expectedRun).getRunDifference();
        differenceParagraph.addRun(differenceRun);

        if (shouldFix) {
            new RunFixer(this.documentRun, this.actualRun, expectedRun, differenceRun).fixRun();
        }
    }
}
