package com.formatChecker.controller;

import com.formatChecker.comparer.differ.TableDiffer;
import com.formatChecker.comparer.model.Difference;
import com.formatChecker.comparer.model.participants.HeadingsList;
import com.formatChecker.config.model.Config;
import com.formatChecker.config.model.participants.Numbering;
import com.formatChecker.config.model.participants.Paragraph;
import com.formatChecker.document.model.DocxDocument;
import com.formatChecker.document.model.data.DocumentData;
import org.docx4j.openpackaging.parts.ThemePart;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableController {
    JAXBElement<Tbl> table;
    P documentParagraph;
    Paragraph<Double, Boolean> actualParagraph;
    Paragraph<Double, Boolean> expectedParagraph;
    Difference difference;
    Boolean shouldFix;
    Integer index;
    Boolean afterTOC;

    Config config;
    DocumentData documentData;
    HeadingsList headings;
    List<String> paragraphsOnNewPages;
    DocxDocument docxDocument;
    Map<Integer, String> configStyles;
    HashMap<Integer, Numbering> numberings;

    Styles styles;
    DocDefaults docDefaults;
    ThemePart themePart;

    public TableController(JAXBElement<Tbl> table,
                               Integer index,
                               Difference difference,
                               DocxDocument docxDocument,
                               DocumentData documentData,
                               Config config,
                               Map<Integer, String> configStyles,
                               HeadingsList headings,
                               List<String> paragraphsOnNewPages,
                               HashMap<Integer, Numbering> numberings,Boolean afterTOC) {
        this.table = table;
        this.index = index;
        this.difference = difference;
        this.documentData = documentData;
        this.headings = headings;
        this.paragraphsOnNewPages = paragraphsOnNewPages;
        this.numberings = numberings;
        this.docxDocument = docxDocument;
        this.config = config;
        this.configStyles = configStyles;
        this.afterTOC = afterTOC;
    }

    public void parseTable() throws Exception {
        for (Object o: table.getValue().getContent()) {
            Tr t = (Tr) o;
            for (Object o2:t.getContent()) {
                JAXBElement<Tc> j_tc = (JAXBElement<Tc>) o2;

                TcPr tcPr = j_tc.getValue().getTcPr();
                compareTable(tcPr);

                for (Object o3: j_tc.getValue().getContent()) {
                    P par = (P) o3;
                    if (!config.getFindHeadingsByTOC() || afterTOC) {
                        ++index;
                        new ParagraphController(index,
                                par,
                                difference,
                                docxDocument,
                                documentData,
                                config,
                                configStyles,
                                headings,
                                paragraphsOnNewPages,
                                numberings).parseParagraph();
                    }
                }
            }
        }
    }

    public void compareTable(TcPr tcPr) {
        String tableDifference = new TableDiffer(tcPr, config.getTable()).getTableDifference();
        if (tableDifference != null)
            difference.addTable(tableDifference);
    }
}
