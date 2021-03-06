package com.formatChecker.parserTests.drawing;

import com.formatChecker.config.model.Config;
import com.formatChecker.config.parser.ConfigParser;
import com.formatChecker.controller.DocumentController;
import com.formatChecker.document.model.DocxDocument;
import com.formatChecker.document.model.participants.Drawing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.formatChecker.parserTests.constants.PathConstants.DRAWING_CONFIG_PATH;
import static com.formatChecker.parserTests.constants.PathConstants.DRAWING_DOCUMENT_PATH;

public class DrawingTest {
    @DisplayName("Checks the result of parsing a drawing with description")
    @Test
    public void testParseDrawing()
            throws Exception {
        DocxDocument docxDocument = new DocumentController(
                DRAWING_CONFIG_PATH,
                DRAWING_DOCUMENT_PATH)
                .getDocxDocument();

        Config config = new ConfigParser(DRAWING_CONFIG_PATH).getConfig();

        for (Drawing<Double, Boolean> drawing : docxDocument.getDrawings().getDrawings()) {
            Assertions.assertEquals(
                    config.getDrawing().getDrawingPosition().getAlignment(),
                    drawing.getDrawing().getAlignment());

            Assertions.assertEquals(
                    config.getDrawing().getDescription().getParagraph().getAlignment(),
                    drawing.getDescription().getAlignment());

            Assertions.assertTrue(
                    drawing.getDescription().getText().contains(
                            config.getDrawing().getTextStartsWith()));

            for (int i = 0; i < drawing.getDescription().getRuns().size(); i++) {
                Assertions.assertEquals(
                        config.getDrawing().getDescription().getParagraph().getRuns().get(0).getFontSize(),
                        drawing.getDescription().getRuns().get(i).getFontSize()
                );
            }
        }
    }
}
