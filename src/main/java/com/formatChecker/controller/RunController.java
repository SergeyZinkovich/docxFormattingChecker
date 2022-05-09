package com.formatChecker.controller;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.comparer.differ.RunDiffer;
import com.formatChecker.comparer.model.Difference;
import com.formatChecker.config.model.Config;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.model.participants.Run;
import com.formatChecker.config.model.participants.Style;
import com.formatChecker.fixer.RunFixer;
import org.docx4j.wml.R;

import java.util.Map;
import java.util.Objects;

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

        if (config.getExistenceConfig() != null && config.getExistenceConfig().getRuns() != null) {
            for (int i = 0; i < config.getExistenceConfig().getRuns().size(); i++) {
                Difference diff = new Difference();
                Paragraph<String, String> paragraph = new Paragraph<>();
                paragraph.addRun(compareRun(config.getExistenceConfig().getRuns().get(i), true));
                diff.addParagraph(paragraph);

                if (Objects.equals(new DifferResultCollector(diff, "run").getDifferenceAsString(), "")) {
                    config.getExistenceConfig().getRuns().remove(i);
                    break;
                }
            }
        }

        if (expectedStyle == null) {
            differenceParagraph.addRun(new Run<String, String>());
            return;
        }

        if (expectedStyle.getParagraph().getRuns() != null && expectedStyle.getParagraph().getRuns().size() > 0) {
            if (expectedStyle.getParagraph().getRuns().size() > runIndex) {
                expectedRun = expectedStyle.getParagraph().getRuns().get(runIndex);
            } else {
                expectedRun = expectedStyle.getParagraph().getRuns().get(expectedStyle.getParagraph().getRuns().size()-1);
            }

            compareRun(expectedRun, false);
        }
    }

    Run<String, String> compareRun(Run<Boolean, Double> expectedRun, Boolean existenceCheck) {
        Run<String, String> differenceRun = new RunDiffer(actualRun, expectedRun).getRunDifference();

        if (shouldFix && !existenceCheck) {
            new RunFixer(this.documentRun, this.actualRun, expectedRun, differenceRun).fixRun();
        }

        if (!existenceCheck) {
            differenceParagraph.addRun(differenceRun);
        }

        return differenceRun;
    }
}
