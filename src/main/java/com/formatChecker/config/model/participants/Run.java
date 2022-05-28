package com.formatChecker.config.model.participants;

public class Run<T, D> {
    String text;

    String fontFamily;
    D fontSize;
    D characterSpacing;

    T bold;
    T italic;
    T strikethrough;

    String underline;

    String vertAlign;
    String textColor;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public D getFontSize() {
        return fontSize;
    }

    public void setFontSize(D fontSize) {
        this.fontSize = fontSize;
    }

    public D getCharacterSpacing() {
        return characterSpacing;
    }

    public void setCharacterSpacing(D characterSpacing) {
        this.characterSpacing = characterSpacing;
    }

    public T getBold() {
        return bold;
    }

    public void setBold(T bold) {
        this.bold = bold;
    }

    public T getItalic() {
        return italic;
    }

    public void setItalic(T italic) {
        this.italic = italic;
    }

    public T getStrikethrough() {
        return strikethrough;
    }

    public void setStrikethrough(T strikethrough) {
        this.strikethrough = strikethrough;
    }

    public String getUnderline() {
        return underline;
    }

    public void setUnderline(String underline) {
        this.underline = underline;
    }

    public String getVertAlign() {
        return vertAlign;
    }

    public void setVertAlign(String vertAlign) {
        this.vertAlign = vertAlign;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
