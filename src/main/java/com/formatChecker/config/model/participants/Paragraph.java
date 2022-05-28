package com.formatChecker.config.model.participants;

import java.util.ArrayList;
import java.util.List;

public class Paragraph<T, D> {
    String id;
    List<Run> runs;
    String text;
    T maxRunsCount;
    T minRunsCount;
    Integer headingLevel;
    D pageBreakBefore;
    String alignment;
    T firstLineIndent;
    T leftIndent;
    T rightIndent;
    T lineSpacing;
    T spacingBefore;
    T spacingAfter;
    Integer numId;
    Integer numLvl;
    public Paragraph() {
        this.runs = new ArrayList<>();
    }

    public void addRun(Run run) {
        runs.add(run);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public T getFirstLineIndent() {
        return firstLineIndent;
    }

    public void setFirstLineIndent(T firstLineIndent) {
        this.firstLineIndent = firstLineIndent;
    }

    public T getLeftIndent() {
        return leftIndent;
    }

    public void setLeftIndent(T leftIndent) {
        this.leftIndent = leftIndent;
    }

    public T getRightIndent() {
        return rightIndent;
    }

    public void setRightIndent(T rightIndent) {
        this.rightIndent = rightIndent;
    }

    public T getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(T lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public T getSpacingBefore() {
        return spacingBefore;
    }

    public void setSpacingBefore(T spacingBefore) {
        this.spacingBefore = spacingBefore;
    }

    public T getSpacingAfter() {
        return spacingAfter;
    }

    public void setSpacingAfter(T spacingAfter) {
        this.spacingAfter = spacingAfter;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getHeadingLevel() {
        return headingLevel;
    }

    public void setHeadingLevel(Integer headingLevel) {
        this.headingLevel = headingLevel;
    }

    public D getPageBreakBefore() {
        return pageBreakBefore;
    }

    public void setPageBreakBefore(D pageBreakBefore) {
        this.pageBreakBefore = pageBreakBefore;
    }

    public T getMaxRunsCount() {
        return maxRunsCount;
    }

    public void setMaxRunsCount(T maxRunsCount) {
        this.maxRunsCount = maxRunsCount;
    }

    public T getMinRunsCount() {
        return minRunsCount;
    }

    public void setMinRunsCount(T minRunsCount) {
        this.minRunsCount = minRunsCount;
    }

    public Integer getNumId() {
        return numId;
    }

    public void setNumId(Integer numId) {
        this.numId = numId;
    }

    public Integer getNumLvl() {
        return numLvl;
    }

    public void setNumLvl(Integer numLvl) {
        this.numLvl = numLvl;
    }
}
