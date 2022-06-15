package com.formatChecker.comparer.differ;

import com.formatChecker.config.model.participants.Table;
import org.docx4j.wml.TcPr;

public class TableDiffer implements Differ{
    TcPr actualTable;
    Table expectedTable;
    String difference;

    public TableDiffer(TcPr actualTable, Table expectedTable) {
        this.actualTable = actualTable;
        this.expectedTable = expectedTable;
        difference = getDifference();
    }

    String getDifference() {
        if (actualTable == null || expectedTable == null || actualTable.getVAlign() == null) {
            return null;
        }

        return checkTextParameter(
                actualTable.getVAlign().getVal().value(),
                expectedTable.getvAlign(),
                "vertical align");
    }

    public String getTableDifference() {
        return difference;
    }
}
