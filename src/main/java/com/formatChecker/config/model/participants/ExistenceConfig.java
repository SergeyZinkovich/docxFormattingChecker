package com.formatChecker.config.model.participants;

import java.util.List;

public class ExistenceConfig {
    List<Paragraph<Double, Boolean>> paragraphs;
    List<Run<Boolean, Double>> runs;

    public List<Run<Boolean, Double>> getRuns() {
        return runs;
    }

    public void setRuns(List<Run<Boolean, Double>> runs) {
        this.runs = runs;
    }

    public List<Paragraph<Double, Boolean>> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph<Double, Boolean>> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
