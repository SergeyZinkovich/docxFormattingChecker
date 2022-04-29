package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.ExistenceConfig;
import com.formatChecker.config.model.participants.Paragraph;

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

    public ArrayList<String> getDifferenceArray() {
        return difference;
    }
}
