package com.formatChecker.controller;

import com.formatChecker.comparer.collector.DifferResultCollector;
import com.formatChecker.comparer.differ.ParagraphDiffer;
import com.formatChecker.comparer.differ.RunsCountDiffer;
import com.formatChecker.comparer.model.Difference;
import com.formatChecker.comparer.model.participants.HeadingsList;
import com.formatChecker.config.model.Config;
import com.formatChecker.config.model.participants.Numbering;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.model.participants.Run;
import com.formatChecker.controller.helper.RunHelper;
import com.formatChecker.document.model.DocxDocument;
import com.formatChecker.document.model.data.DocumentData;
import com.formatChecker.document.parser.paragraph.ParagraphDirectParser;
import com.formatChecker.fixer.ParagraphFixer;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.parts.ThemePart;
import org.docx4j.wml.DocDefaults;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Styles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ParagraphController implements RunHelper {
    private static final String HEADING_STYLE_NAME = "heading";
    private static final String BODY_STYLE_NAME = "body";

    P documentParagraph;
    Paragraph<Double, Boolean> actualParagraph;
    Paragraph<Double, Boolean> expectedParagraph;
    Difference difference;
    Boolean shouldFix;
    Integer index;

    Config config;
    DocumentData documentData;
    HeadingsList headings;
    List<String> paragraphsOnNewPages;
    DocxDocument docxDocument;
    Map<Integer, String> configStyles;
    HashMap<Integer, Numbering> numberings;

    Styles styles;
    DocDefaults docDefaults;
    ThemePart themePart;


    public ParagraphController(Integer index,
                               P documentParagraph,
                               Difference difference,
                               DocxDocument docxDocument,
                               DocumentData documentData,
                               Config config,
                               Map<Integer, String> configStyles,
                               HeadingsList headings,
                               List<String> paragraphsOnNewPages,
                               HashMap<Integer, Numbering> numberings) throws Docx4JException {
        this.index = index;
        this.documentParagraph = documentParagraph;

        this.difference = difference;
        this.documentData = documentData;
        this.headings = headings;
        this.paragraphsOnNewPages = paragraphsOnNewPages;
        this.numberings = numberings;
        this.docxDocument = docxDocument;
        this.config = config;
        this.configStyles = configStyles;
        this.shouldFix = config.getGenerateNewDocument();

        this.styles = documentData.getStyles();
        this.docDefaults = documentData.getDocDefaults();
        this.themePart = documentData.getThemePart();

        this.actualParagraph = new ParagraphDirectParser(docDefaults, styles, themePart, documentParagraph, headings, paragraphsOnNewPages)
                .parseParagraph();
    }

    public void parseParagraph() {
        docxDocument.addParagraph(actualParagraph);
        expectedParagraph = new Paragraph<>();

        if (configStyles == null) {
            if (actualParagraph.getHeadingLevel() > 0) {
                expectedParagraph = config.getStyles().get(HEADING_STYLE_NAME + actualParagraph.getHeadingLevel())
                        .getParagraph();
            } else {
                expectedParagraph = config.getStyles().get(BODY_STYLE_NAME).getParagraph();
            }
        } else if (configStyles.get(index) != null) {
            expectedParagraph = config.getStyles().get(configStyles.get(index)).getParagraph();
        }

        compareParagraph(expectedParagraph, false);

        if (config.getExistenceConfig() != null && config.getExistenceConfig().getParagraphs() != null) {
            for (int i = 0; i < config.getExistenceConfig().getParagraphs().size(); i++) {
                Difference diff = new Difference();
                diff.addParagraph(compareParagraph(config.getExistenceConfig().getParagraphs().get(i), true));

                if (Objects.equals(new DifferResultCollector(diff, "paragraph").getDifferenceAsString(), "")) {
                    config.getExistenceConfig().getParagraphs().remove(i);
                    break;
                }
            }
        }
    }

    Paragraph<String, String> compareParagraph(Paragraph<Double, Boolean> expectedParagraph, Boolean existenceCheck) {
        Paragraph<String, String> differenceParagraph = new ParagraphDiffer(actualParagraph, expectedParagraph)
                .getParagraphDifference();

        setUsedNumbering();

        if (shouldFix && !existenceCheck) {
            new ParagraphFixer(documentParagraph, actualParagraph, expectedParagraph, differenceParagraph)
                    .fixParagraph();
        }

        int count = 0;
        for (Object o : documentParagraph.getContent()) {
            if (o instanceof R) {
                R r = (R) o;
                if (!StringUtils.isBlank(getText(r))) {
                    ++count;

                    Run<Boolean, Double> actualRun = (Run<Boolean, Double>) actualParagraph.getRuns().get(count - 1);
                    new RunController(index, count - 1, r, actualRun, differenceParagraph, configStyles,
                            actualParagraph.getHeadingLevel(), config, shouldFix).parseRun();
                }
            }
        }

        differenceParagraph.setMinRunsCount(new RunsCountDiffer(count, expectedParagraph.getMinRunsCount(), expectedParagraph.getMaxRunsCount()).getDifference());

        if (!existenceCheck) {
            difference.addParagraph(differenceParagraph);
        }

        return differenceParagraph;
    }

    void setUsedNumbering() {
        if (actualParagraph.getNumId() != null) {
            numberings.get(actualParagraph.getNumId()).setUsed(true);
            numberings.get(actualParagraph.getNumId()).getNumberingLevels().get(actualParagraph.getNumLvl()).setUsed(true);
        }
    }
}
