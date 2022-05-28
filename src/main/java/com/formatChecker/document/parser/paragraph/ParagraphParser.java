package com.formatChecker.document.parser.paragraph;

import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.document.converter.ValuesConverter;
import org.docx4j.wml.DocDefaults;
import org.docx4j.wml.Jc;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.Ind;
import org.docx4j.wml.PPrBase.Spacing;

import java.math.BigInteger;

public abstract class ParagraphParser implements ValuesConverter {
    DocDefaults docDefaults;

    Paragraph<Double, Boolean> paragraph;
    PPr paragraphProperties;

    public ParagraphParser(DocDefaults docDefaults) {
        this.docDefaults = docDefaults;

        this.paragraph = new Paragraph<>();
    }


    String getAlignment(PPr paragraphProperties) {
        if (paragraphProperties == null)
            return null;
        else {
            Jc alignment = paragraphProperties.getJc();
            if (alignment == null)
                return null;
            else
                return convertAlignment(alignment.getVal().toString());
        }
    }

    Ind getIndent(PPr paragraphProperties) {
        if (paragraphProperties == null)
            return null;
        else
            return paragraphProperties.getInd();
    }

    Double getFirstLineIndent(Ind indent) {
        if (indent == null)
            return null;
        else {
            BigInteger firstLineIndent = indent.getFirstLine();
            return firstLineIndent == null ? null : twipsToCm(firstLineIndent);
        }
    }

    Double getLeftIndent(Ind indent) {
        if (indent == null)
            return null;
        else {
            BigInteger leftIndent = indent.getLeft();
            return leftIndent == null ? null : twipsToCm(leftIndent);
        }
    }

    Double getRightIndent(Ind indent) {
        if (indent == null)
            return null;
        else {
            BigInteger rightIndent = indent.getRight();
            return rightIndent == null ? null : twipsToCm(rightIndent);
        }
    }

    Spacing getSpacing(PPr paragraphProperties) {
        if (paragraphProperties == null)
            return null;
        else
            return paragraphProperties.getSpacing();
    }

    Double getLineSpacing(Spacing spacing) {
        if (spacing == null)
            return null;
        else {
            BigInteger lineSpacing = spacing.getLine();
            return lineSpacing == null ? null : lineSpacingValToCm(lineSpacing);
        }
    }

    Double getSpacingBefore(Spacing spacing) {
        if (spacing == null)
            return null;
        else {
            BigInteger spacingBefore = spacing.getBefore();
            return spacingBefore == null ? null : absUnitsToCm(spacingBefore);
        }
    }

    Double getSpacingAfter(Spacing spacing) {
        if (spacing == null)
            return null;
        else {
            BigInteger spacingAfter = spacing.getAfter();
            return spacingAfter == null ? null : absUnitsToCm(spacingAfter);
        }
    }

    Integer getNumId(PPr paragraphProperties) {
        if (paragraphProperties != null && paragraphProperties.getNumPr() != null) {
            return paragraphProperties.getNumPr().getNumId().getVal().intValue();
        } else {
            return null;
        }
    }

    Integer getNumLvl(PPr paragraphProperties) {
        if (paragraphProperties != null && paragraphProperties.getNumPr() != null) {
            return paragraphProperties.getNumPr().getIlvl().getVal().intValue();
        } else {
            return null;
        }
    }

    Paragraph<Double, Boolean> getDefaultProperties(DocDefaults docDefaults) {
        return new ParagraphDefaultsParser(docDefaults).parseParagraph();
    }
}
