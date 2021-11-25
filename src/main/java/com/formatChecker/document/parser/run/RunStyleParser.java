package com.formatChecker.document.parser.run;

import com.formatChecker.config.model.participants.Run;
import com.formatChecker.document.parser.styles.StyleHierarchy;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.parts.ThemePart;
import org.docx4j.wml.DocDefaults;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Style;
import org.docx4j.wml.Styles;

import java.util.Optional;

public class RunStyleParser extends RunParser implements RunSetProperties, StyleHierarchy {
    String styleId;
    Styles styles;

    Run<Boolean, Double> defaultRun;
    Optional<Style> style;
    Optional<Style> parentStyle;

    public RunStyleParser(DocDefaults docDefaults,
                          ThemePart themePart,
                          String styleId,
                          Styles styles) throws Docx4JException {
        super(docDefaults, themePart);

        this.style = getStyleById(styleId, styles);
        this.runProperties = getRunProperties(style);

        this.styleId = styleId;
        this.styles = styles;
        this.parentStyle = getParentStyle(style, styles);

        this.defaultRun = getDefaultProperties(docDefaults, themePart);
    }

    public Run<Boolean, Double> parseRun() throws Docx4JException {
        setFontFamily();
        setFontSize();
        setCharacterSpacing();
        setBold();
        setItalic();
        setStrikethrough();
        setUnderline();
        setVertAlign();
        setTextColor();

        return run;
    }

    RPr getRunProperties(Optional<Style> style) {
        return style.map(Style::getRPr).orElse(null);
    }

    @Override
    public void setFontFamily() throws Docx4JException {
        String fontFamily = getFontFamily(runProperties, themePart);

        while (parentStyle != null && fontFamily == null) {
            fontFamily = getFontFamily(getRunProperties(parentStyle), themePart);
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (fontFamily == null) {
            fontFamily = defaultRun.getFontFamily();
        }

        run.setFontFamily(fontFamily);
    }

    @Override
    public void setFontSize() {
        Double fontSize = getFontSize(runProperties);

        while (parentStyle != null && fontSize == null) {
            fontSize = getFontSize(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (fontSize == null) {
            fontSize = defaultRun.getFontSize();
        }

        run.setFontSize(fontSize);
    }

    @Override
    public void setCharacterSpacing() {
        Double characterSpacing = getCharacterSpacing(runProperties);

        while (parentStyle != null && characterSpacing == null) {
            characterSpacing = getCharacterSpacing(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (characterSpacing == null) {
            characterSpacing = defaultRun.getCharacterSpacing();
        }

        run.setCharacterSpacing(characterSpacing);
    }

    @Override
    public void setBold() {
        Boolean isBold = getBold(runProperties);

        while (parentStyle != null && isBold == null) {
            isBold = getBold(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (isBold == null) {
            isBold = defaultRun.getBold();
        }

        run.setBold(isBold);
    }

    @Override
    public void setItalic() {
        Boolean isItalic = getItalic(runProperties);

        while (parentStyle != null && isItalic == null) {
            isItalic = getItalic(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (isItalic == null) {
            isItalic = defaultRun.getItalic();
        }

        run.setItalic(isItalic);
    }

    @Override
    public void setStrikethrough() {
        Boolean isStrikethrough = getStrikethrough(runProperties);

        while (parentStyle != null && isStrikethrough == null) {
            isStrikethrough = getStrikethrough(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (isStrikethrough == null) {
            isStrikethrough = defaultRun.getStrikethrough();
        }

        run.setStrikethrough(isStrikethrough);
    }

    @Override
    public void setUnderline() {
        String underline = getUnderline(runProperties);

        while (parentStyle != null && underline == null) {
            underline = getUnderline(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (underline == null) {
            underline = defaultRun.getUnderline();
        }

        run.setUnderline(underline);
    }

    @Override
    public void setVertAlign() {
        String vertAlign = getVertAlign(runProperties);

        while (parentStyle != null && vertAlign == null) {
            vertAlign = getVertAlign(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (vertAlign == null) {
            vertAlign = defaultRun.getVertAlign();
        }

        run.setVertAlign(vertAlign);
    }

    @Override
    public void setTextColor() {
        String textColor = getTextColor(runProperties);

        while (parentStyle != null && textColor == null) {
            textColor = getTextColor(getRunProperties(parentStyle));
            parentStyle = getParentStyle(parentStyle, styles);
        }

        if (textColor == null) {
            textColor = defaultRun.getTextColor();
        }

        run.setTextColor(textColor);
    }
}
