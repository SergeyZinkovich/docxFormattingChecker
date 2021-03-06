package com.formatChecker.document.parser.run;

import com.formatChecker.document.converter.ValuesConverter;
import org.docx4j.openpackaging.exceptions.Docx4JException;

public interface RunSetProperties extends ValuesConverter {
    void setFontFamily() throws Docx4JException;

    void setFontSize();

    void setCharacterSpacing();

    void setBold();

    void setItalic();

    void setStrikethrough();

    void setUnderline();

    void setVertAlign();

    void setTextColor();
}
