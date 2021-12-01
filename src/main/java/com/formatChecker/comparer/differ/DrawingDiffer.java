package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.ConfigDrawing;
import com.formatChecker.config.model.participants.Run;
import com.formatChecker.document.model.participants.Drawing;

public class DrawingDiffer {
    private static final String DESCRIPTION_EXTRA_WORDS = "(ARABIC | SEQ| Рисунок \\\\\\*)";

    Drawing<Double, Boolean> actualDrawing;
    ConfigDrawing expectedDrawing;
    Drawing<String, String> differenceDrawing;

    public DrawingDiffer(Drawing<Double, Boolean> actualDrawing, ConfigDrawing expectedDrawing) {
        this.actualDrawing = actualDrawing;
        this.expectedDrawing = expectedDrawing;
        this.differenceDrawing = getDifference();
    }

    Drawing<String, String> getDifference() {
        Drawing<String, String> drawing = new Drawing<>();

        if (actualDrawing == null && expectedDrawing != null)
            return null;
        else if (actualDrawing != null && expectedDrawing == null)
            return  null;
        else {
            if (actualDrawing.getText() != null)
                drawing.setText(actualDrawing.getText().replaceAll(DESCRIPTION_EXTRA_WORDS, ""));

            drawing.setTextErrorMessage(compareText());

            drawing.setDrawing(new ParagraphDiffer(
                    actualDrawing.getDrawing(),
                    expectedDrawing.getDrawingPosition())
                    .getParagraphDifference());

            drawing.setDescription(new ParagraphDiffer(
                    actualDrawing.getDescription(),
                    expectedDrawing.getDescription().getParagraph())
                    .getParagraphDifference());

            for (int i = 0; i < actualDrawing.getDescription().getRuns().size(); i++) {
                Run<Boolean, Double> expectedRun = new Run<>();
                if (expectedDrawing.getDescription().getParagraph().getRuns().size() > i) {
                    expectedRun = expectedDrawing.getDescription().getParagraph().getRuns().get(i);
                } else if (expectedDrawing.getDescription().getParagraph().getRuns().size() > 0) {
                    expectedRun = expectedDrawing.getDescription().getParagraph().getRuns().get(
                            expectedDrawing.getDescription().getParagraph().getRuns().size() - 1
                    );
                }

                drawing.getDescription().addRun(
                        new RunDiffer(
                                actualDrawing.getDescription().getRuns().get(i),
                                expectedRun
                        ).getRunDifference()
                );
            }

            return drawing;
        }
    }

    String compareText() {
        String expectedTextStart = expectedDrawing.getTextStartsWith();
        String result = "";

        if (actualDrawing.getText() == null || actualDrawing.getText().equals(""))
            return "add a drawing description";

        if (!actualDrawing.getText().startsWith(expectedTextStart))
            result = "drawing description text should start with " + expectedTextStart;

        return result;
    }

    public Drawing<String, String> getDifferenceDrawing() {
        return differenceDrawing;
    }
}
