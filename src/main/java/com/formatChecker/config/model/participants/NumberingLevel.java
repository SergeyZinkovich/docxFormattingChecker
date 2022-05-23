package com.formatChecker.config.model.participants;

public class NumberingLevel {
    String type;
    String text;
    Boolean used = false;

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public Boolean getUsed() {
        return used;
    }
}
