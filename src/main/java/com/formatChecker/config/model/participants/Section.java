package com.formatChecker.config.model.participants;

import java.util.List;

public class Section<T> {
    protected List<T> margins;
    protected T pageHeight;
    protected T pageWidth;
    String orientation;

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<T> getMargins() {
        return margins;
    }

    public void setMargins(List<T> margins) {
        this.margins = margins;
    }

    public T getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(T pageHeight) {
        this.pageHeight = pageHeight;
    }

    public T getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(T pageWidth) {
        this.pageWidth = pageWidth;
    }
}
