package com.formatChecker.document.model;

import com.formatChecker.comparer.model.participants.DrawingsList;
import com.formatChecker.config.model.participants.Footer;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.model.participants.Section;

import java.util.ArrayList;
import java.util.List;

public class DocxDocument {
    List<Paragraph<Double, Boolean>> paragraphs;
    Integer pages;
    List<Section<Double>> sections;
    Footer footer;
    DrawingsList drawings;
    public DocxDocument() {
        this.paragraphs = new ArrayList<>();
        this.sections = new ArrayList<>();
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void addParagraph(Paragraph<Double, Boolean> paragraph) {
        paragraphs.add(paragraph);
    }

    public void addSection(Section<Double> section) {
        sections.add(section);
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public List<Paragraph<Double, Boolean>> getParagraphs() {
        return paragraphs;
    }

    public List<Section<Double>> getSections() {
        return sections;
    }

    public DrawingsList getDrawings() {
        return drawings;
    }

    public void setDrawings(DrawingsList drawings) {
        this.drawings = drawings;
    }
}
