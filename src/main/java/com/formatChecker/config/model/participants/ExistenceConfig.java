package com.formatChecker.config.model.participants;

import java.util.List;

public class ExistenceConfig {
    List<Paragraph<Double, Boolean>> paragraphs;

    public List<Paragraph<Double, Boolean>> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph<Double, Boolean>> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
