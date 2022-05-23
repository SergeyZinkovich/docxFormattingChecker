package com.formatChecker.config.model.participants;

import org.docx4j.wml.Numbering;
import org.docx4j.wml.PPrBase;

import java.util.ArrayList;
import java.util.List;

public class Paragraph<T, D> {
    public Paragraph() {
        this.runs = new ArrayList<>();
    }

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

    public void addRun(Run run) {
        runs.add(run);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHeadingLevel(Integer headingLevel) {
        this.headingLevel = headingLevel;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public void setFirstLineIndent(T firstLineIndent) {
        this.firstLineIndent = firstLineIndent;
    }

    public void setLeftIndent(T leftIndent) {
        this.leftIndent = leftIndent;
    }

    public void setRightIndent(T rightIndent) {
        this.rightIndent = rightIndent;
    }

    public void setLineSpacing(T lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setSpacingBefore(T spacingBefore) {
        this.spacingBefore = spacingBefore;
    }

    public void setSpacingAfter(T spacingAfter) {
        this.spacingAfter = spacingAfter;
    }

    public void setPageBreakBefore(D pageBreakBefore) {
        this.pageBreakBefore = pageBreakBefore;
    }

    public void setMaxRunsCount(T maxRunsCount) {
        this.maxRunsCount = maxRunsCount;
    }

    public void setMinRunsCount(T minRunsCount) {
        this.minRunsCount = minRunsCount;
    }

    public void setNumId(Integer numId) {
        this.numId = numId;
    }

    public void setNumLvl(Integer numLvl) {
        this.numLvl = numLvl;
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

    public T getFirstLineIndent() {
        return firstLineIndent;
    }

    public T getLeftIndent() {
        return leftIndent;
    }

    public T getRightIndent() {
        return rightIndent;
    }

    public T getLineSpacing() {
        return lineSpacing;
    }

    public T getSpacingBefore() {
        return spacingBefore;
    }

    public T getSpacingAfter() {
        return spacingAfter;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public String getText() {
        return text;
    }

    public Integer getHeadingLevel() {
        return headingLevel;
    }

    public D getPageBreakBefore() {
        return pageBreakBefore;
    }

    public T getMaxRunsCount() {
        return maxRunsCount;
    }

    public T getMinRunsCount() {
        return minRunsCount;
    }

    public Integer getNumId() {
        return numId;
    }

    public Integer getNumLvl() {
        return numLvl;
    }
}
