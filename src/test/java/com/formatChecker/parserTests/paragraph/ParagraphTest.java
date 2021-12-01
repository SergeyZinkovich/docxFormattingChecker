package com.formatChecker.parserTests.paragraph;

import com.formatChecker.config.model.Config;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.parser.ConfigParser;
import com.formatChecker.controller.DocumentController;
import com.formatChecker.document.model.DocxDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.parserTests.constants.PathConstants.*;

public class ParagraphTest {
    @DisplayName("Checks the result of parsing paragraphs by indexes")
    @Test
    public void testParseParagraphsByIndexes()
            throws Exception {
        DocxDocument docxDocument = new DocumentController(
                PARAGRAPH_BY_INDEX_CONFIG_PATH,
                PARAGRAPH_BY_INDEX_DOCUMENT_PATH)
                .getDocxDocument();

        Config config = new ConfigParser(PARAGRAPH_BY_INDEX_CONFIG_PATH).getConfig();

        Paragraph<Double, Boolean> paragraph1 = docxDocument.getParagraphs().get(0);
        Paragraph<Double, Boolean> paragraph2 = docxDocument.getParagraphs().get(1);

        Assertions.assertEquals(
                config.getStyles().get("style1").getParagraph().getAlignment(),
                paragraph1.getAlignment());

        Assertions.assertEquals(
                config.getStyles().get("style2").getParagraph().getFirstLineIndent(),
                paragraph2.getFirstLineIndent());

        for (int i = 0; i < paragraph1.getRuns().size(); ++i) {
            Assertions.assertEquals(
                    config.getStyles().get("style1").getParagraph().getRuns().get(0).getFontFamily(),
                    paragraph1.getRuns().get(i).getFontFamily());

            Assertions.assertEquals(
                    config.getStyles().get("style1").getParagraph().getRuns().get(0).getBold(),
                    paragraph1.getRuns().get(i).getBold());
        }
        for (int i = 0; i < paragraph2.getRuns().size(); ++i) {
            Assertions.assertEquals(
                    config.getStyles().get("style2").getParagraph().getRuns().get(0).getFontSize(),
                    paragraph2.getRuns().get(i).getFontSize());
        }
    }

    @DisplayName("Checks the result of parsing paragraphs by headings")
    @Test
    public void testParseParagraphsByHeadings()
            throws Exception {
        DocxDocument docxDocument = new DocumentController(
                PARAGRAPH_BY_HEADING_CONFIG_PATH,
                PARAGRAPH_BY_HEADING_DOCUMENT_PATH)
                .getDocxDocument();

        Config config = new ConfigParser(PARAGRAPH_BY_HEADING_CONFIG_PATH).getConfig();

        Paragraph<Double, Boolean> headingParagraph = new Paragraph<>();
        Paragraph<Double, Boolean> bodyParagraph = new Paragraph<>();

        for (Paragraph<Double, Boolean> paragraph : docxDocument.getParagraphs()) {
            if (paragraph.getHeadingLevel() > 0)
                headingParagraph = paragraph;
            else
                bodyParagraph = paragraph;
        }

        Assertions.assertEquals(
                config.getStyles().get("heading1").getParagraph().getAlignment(),
                headingParagraph.getAlignment());

        Assertions.assertEquals(
                config.getStyles().get("body").getParagraph().getFirstLineIndent(),
                bodyParagraph.getFirstLineIndent());

        for (int i = 0; i < headingParagraph.getRuns().size(); ++i) {
            Assertions.assertEquals(
                    config.getStyles().get("heading1").getParagraph().getRuns().get(0).getFontFamily(),
                    headingParagraph.getRuns().get(i).getFontFamily());

            Assertions.assertEquals(
                    config.getStyles().get("heading1").getParagraph().getRuns().get(0).getBold(),
                    headingParagraph.getRuns().get(i).getBold());
        }
        for (int i = 0; i < bodyParagraph.getRuns().size(); ++i) {
            Assertions.assertEquals(
                    config.getStyles().get("body").getParagraph().getRuns().get(0).getFontSize(),
                    bodyParagraph.getRuns().get(i).getFontSize());
        }
    }
}
