package com.formatChecker.config.model.participants;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Style {
    @SerializedName("paragraphs")
    List<Integer> paragraphIndexes;

    @SerializedName("paragraphProperties")
    Paragraph<Double, Boolean> paragraph;

    public List<Integer> getParagraphIndexes() {
        return paragraphIndexes;
    }

    public Paragraph<Double, Boolean> getParagraph() {
        return paragraph;
    }
}
