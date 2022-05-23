package com.formatChecker.document.parser.numbering;

import com.formatChecker.config.model.participants.NumberingLevel;
import org.docx4j.wml.Lvl;
import org.docx4j.wml.Numbering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberingDefinitionParser {
    HashMap<Integer, com.formatChecker.config.model.participants.Numbering> numberings;
    Numbering numberingDefinitionPart;

    public NumberingDefinitionParser(Numbering numberingDefinitionsPart) {
        this.numberingDefinitionPart = numberingDefinitionsPart;
        this.numberings = parseNumberings();
    }

    HashMap<Integer, com.formatChecker.config.model.participants.Numbering> parseNumberings() {
        HashMap<Integer, com.formatChecker.config.model.participants.Numbering> numberings = new HashMap<>();

        if (numberingDefinitionPart == null) {
            return numberings;
        }

        for (Numbering.Num num : numberingDefinitionPart.getNum()) {
            Numbering.AbstractNum abstractNum = getAbstractNumById(num.getAbstractNumId().getVal().intValue(), numberingDefinitionPart.getAbstractNum());

            com.formatChecker.config.model.participants.Numbering numbering = new com.formatChecker.config.model.participants.Numbering();
            numbering.setNumberingLevels(parseNumberingLevels(abstractNum.getLvl()));
            numbering.setId(num.getNumId().intValue());
            numberings.put(numbering.getId(), numbering);
        }

        return numberings;
    }

    ArrayList<NumberingLevel> parseNumberingLevels(List<Lvl> levels) {
        ArrayList<NumberingLevel> numberingLevels = new ArrayList<>();

        for (Lvl level : levels) {
            NumberingLevel lvl = new NumberingLevel();
            lvl.setType(level.getNumFmt().getVal().value());
            lvl.setText(level.getLvlText().getVal());

            numberingLevels.add(lvl);
        }

        return numberingLevels;
    }

    Numbering.AbstractNum getAbstractNumById(Integer id, List<Numbering.AbstractNum> abstractNums) {
        if (abstractNums.get(id).getAbstractNumId().intValue() == id) {
            return abstractNums.get(id);
        }

        for (Numbering.AbstractNum abstractNum : abstractNums) {
            if (abstractNum.getAbstractNumId().intValue() == id) {
                return abstractNum;
            }
        }

        return null;
    }

    public HashMap<Integer, com.formatChecker.config.model.participants.Numbering> getNumberings() {
        return numberings;
    }
}
