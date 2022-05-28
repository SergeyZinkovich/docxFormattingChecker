package com.formatChecker.config.model.participants;

import java.util.ArrayList;
import java.util.List;

public class Numbering {
    Integer id;
    List<NumberingLevel> numberingLevels;
    Boolean used = false;

    public Numbering() {
        this.numberingLevels = new ArrayList<>();
    }

    public void addNumberingLevel(NumberingLevel numberingLevel) {
        this.numberingLevels.add(numberingLevel);
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<NumberingLevel> getNumberingLevels() {
        return numberingLevels;
    }

    public void setNumberingLevels(List<NumberingLevel> numberingLevels) {
        this.numberingLevels = numberingLevels;
    }
}
