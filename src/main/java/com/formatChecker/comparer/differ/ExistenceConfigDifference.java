package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.ExistenceConfig;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.model.participants.Run;

import java.util.ArrayList;

public class ExistenceConfigDifference {
    ExistenceConfig existenceConfig;
    ArrayList<String> difference;

    public ExistenceConfigDifference(ExistenceConfig existenceConfig) {
        this.existenceConfig = existenceConfig;
        this.difference = getDifference();
    }

    ArrayList<String> getDifference() {
        ArrayList<String> result = new ArrayList<>();
        result.addAll(getParagraphsDifference());
        result.addAll(getRunsDifference());

        return result;
    }

    ArrayList<String> getParagraphsDifference() {
        ArrayList<String> result = new ArrayList<>();

        if (existenceConfig.getParagraphs() == null) {
            return result;
        }

        for(Paragraph<Double, Boolean> paragraph: existenceConfig.getParagraphs()) {
            String paragraphResult = "Doesn't exist paragraph with params: ";

            if (paragraph.getText() != null)
                paragraphResult += "Text = " + paragraph.getText() + " ,";

            if (paragraph.getAlignment() != null)
                paragraphResult += "Alignment = " + paragraph.getAlignment() + " ,";

            if (paragraph.getFirstLineIndent() != null)
                paragraphResult += "FirstLineIndent = " + paragraph.getFirstLineIndent() + " ,";

            if (paragraph.getLeftIndent() != null)
                paragraphResult += "LeftIndent = " + paragraph.getLeftIndent() + " ,";

            if (paragraph.getRightIndent() != null)
                paragraphResult += "RightIndent = " + paragraph.getRightIndent() + " ,";

            if (paragraph.getLineSpacing() != null)
                paragraphResult += "LineSpacing = " + paragraph.getLineSpacing() + " ,";

            if (paragraph.getSpacingBefore() != null)
                paragraphResult += "SpacingBefore = " + paragraph.getSpacingBefore() + " ,";

            if (paragraph.getSpacingAfter() != null)
                paragraphResult += "SpacingAfter = " + paragraph.getSpacingAfter() + " ,";

            if (paragraph.getPageBreakBefore() != null)
                paragraphResult += "PageBreakBefore = " + paragraph.getPageBreakBefore() + " ,";

            if (paragraph.getMinRunsCount() != null)
                paragraphResult += "MinRunsCount = " + paragraph.getMinRunsCount() + " ,";

            result.add(paragraphResult);
        }

        return result;
    }

    ArrayList<String> getRunsDifference() {
        ArrayList<String> result = new ArrayList<>();

        if (existenceConfig.getRuns() == null) {
            return result;
        }

        for(Run<Boolean, Double> run: existenceConfig.getRuns()) {
            String runResult = "Doesn't exist run with params: ";

            if (run.getText() != null)
                runResult += "Text = " + run.getText() + " ,";

            if (run.getFontFamily() != null)
                runResult += "FontFamily = " + run.getFontFamily() + " ,";

            if (run.getFontSize() != null)
                runResult += "FontSize = " + run.getFontSize() + " ,";

            if (run.getBold() != null)
                runResult += "Bold = " + run.getBold() + " ,";

            if (run.getItalic() != null)
                runResult += "Italic = " + run.getItalic() + " ,";

            if (run.getStrikethrough() != null)
                runResult += "Strikethrough = " + run.getStrikethrough() + " ,";

            if (run.getUnderline() != null)
                runResult += "Underline = " + run.getUnderline() + " ,";

            if (run.getVertAlign() != null)
                runResult += "VertAlign = " + run.getVertAlign() + " ,";

            if (run.getTextColor() != null)
                runResult += "TextColor = " + run.getTextColor() + " ,";

            if (run.getCharacterSpacing() != null)
                runResult += "CharacterSpacing = " + run.getCharacterSpacing() + " ,";

            result.add(runResult);
        }

        return result;
    }

    public ArrayList<String> getDifferenceArray() {
        return difference;
    }
}
