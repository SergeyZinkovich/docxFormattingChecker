package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.Numbering;
import com.formatChecker.config.model.participants.NumberingLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class NumberingDiffer {
    HashMap<Integer, Numbering> actualNumbering;
    List<Numbering> expectedNumbering;
    String difference;

    public NumberingDiffer(HashMap<Integer, Numbering> actualNumbering, List<Numbering> expectedNumbering) {
        this.actualNumbering = actualNumbering;
        this.expectedNumbering = expectedNumbering;
        this.difference = compareAllNumberings();
    }

    String compareAllNumberings() {
        String difference = "";

        if (expectedNumbering == null) {
            return difference;
        }

        for (Numbering num1: actualNumbering.values()) {
            if (!num1.getUsed()) {
                continue;
            }

            boolean b = true;
            for (Numbering num2: expectedNumbering) {
                b &= compareNumbering(num1, num2);
            }

            if (!b) {
                difference += "Numbering " + num1.getId().toString() + " has wrong format" + "\n\t";
            }
        }

        return difference;
    }

    Boolean compareNumbering(Numbering num1, Numbering num2) {
        boolean b = true;

        for (int i = 0; i < num1.getNumberingLevels().size(); i++) {
            NumberingLevel lvl = num1.getNumberingLevels().get(i);
            if (!lvl.getUsed()) {
                continue;
            }

            b &= Objects.equals(lvl.getType(), num2.getNumberingLevels().get(i).getType());
            b &= Objects.equals(lvl.getText(), num2.getNumberingLevels().get(i).getText());
        }

        return b;
    }

    public String getDifference() {
        return difference;
    }
}
