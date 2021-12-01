package com.formatChecker.config.model.participants;

public class ParagraphsCount {
    Integer min;
    Integer max;

    public ParagraphsCount(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }
}
