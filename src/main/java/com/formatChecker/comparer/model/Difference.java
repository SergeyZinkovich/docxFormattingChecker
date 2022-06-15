package com.formatChecker.comparer.model;

import com.formatChecker.comparer.model.participants.DrawingsList;
import com.formatChecker.comparer.model.participants.HeadingsList;
import com.formatChecker.config.model.participants.Footer;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.config.model.participants.Section;

import java.util.ArrayList;
import java.util.List;

public class Difference {
    String filename;
    String pages;
    String paragraphsCount;
    String numbering;

    List<String> tables;
    List<Section<String>> sections;
    List<Paragraph<String, String>> paragraphs;
    Footer footer;
    DrawingsList drawings;
    HeadingsList headings;
    ArrayList<String> existenceConfig;

    public Difference() {
        this.paragraphs = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.existenceConfig = new ArrayList<>();
        this.tables = new ArrayList<>();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getParagraphsCount() {
        return paragraphsCount;
    }

    public void setParagraphsCount(String paragraphsCount) {
        this.paragraphsCount = paragraphsCount;
    }

    public List<Section<String>> getSections() {
        return sections;
    }

    public List<Paragraph<String, String>> getParagraphs() {
        return paragraphs;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public DrawingsList getDrawings() {
        return drawings;
    }

    public void setDrawings(DrawingsList drawings) {
        this.drawings = drawings;
    }

    public HeadingsList getHeadings() {
        return headings;
    }

    public void setHeadings(HeadingsList headings) {
        this.headings = headings;
    }

    public void addParagraph(Paragraph<String, String> paragraph) {
        paragraphs.add(paragraph);
    }

    public void addSection(Section<String> section) {
        sections.add(section);
    }

    public ArrayList<String> getExistenceConfig() {
        return existenceConfig;
    }

    public void addExistenceConfig(ArrayList<String> existenceConfig) {
        this.existenceConfig.addAll(existenceConfig);
    }

    public String getNumbering() {
        return numbering;
    }

    public void setNumbering(String numbering) {
        this.numbering = numbering;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public void addTable(String table) {
        this.tables.add(table);
    }
}
