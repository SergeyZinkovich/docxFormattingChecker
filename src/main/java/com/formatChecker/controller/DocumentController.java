package com.formatChecker.controller;

import com.formatChecker.comparer.differ.*;
import com.formatChecker.comparer.model.Difference;
import com.formatChecker.comparer.model.participants.HeadingsList;
import com.formatChecker.config.model.Config;
import com.formatChecker.config.model.participants.Numbering;
import com.formatChecker.config.parser.ConfigParser;
import com.formatChecker.document.model.DocxDocument;
import com.formatChecker.document.model.data.DocumentData;
import com.formatChecker.document.model.participants.raw.DrawingsRawList;
import com.formatChecker.document.parser.DocxParser;
import com.formatChecker.document.parser.numbering.NumberingDefinitionParser;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.P;
import org.docx4j.wml.SdtBlock;
import org.docx4j.wml.SectPr;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentController {
    String docxPath;

    WordprocessingMLPackage wordMLPackage;
    DocxDocument docxDocument;
    Difference difference;

    ConfigParser configParser;
    Config config;
    Map<Integer, String> configStyles;
    Boolean shouldFix;

    DocxParser docxParser;
    DocumentData documentData;
    NumberingDefinitionParser numberingDefinitionParser;
    HashMap<Integer, Numbering> numberings;
    List<SectPr> sectionsProperties;
    List<String> paragraphOnNewPageIds;
    DrawingsRawList paragraphsWithDrawings;

    HeadingsList headings;

    int ParagraphsCount = 0;

    public DocumentController(String configPath, String docxPath)
            throws Exception {
        this.docxPath = docxPath;

        this.configParser = new ConfigParser(configPath);
        this.config = configParser.getConfig();
        this.configStyles = configParser.getStyles();
        this.shouldFix = configParser.getShouldFix();

        this.wordMLPackage = Docx4J.load(new FileInputStream(docxPath));

        this.docxParser = new DocxParser(wordMLPackage);
        this.headings = configParser.getFindHeadingsByTOC() != null ? docxParser.getHeadings() : null;
        this.documentData = docxParser.getDocumentData();
        this.numberingDefinitionParser = new NumberingDefinitionParser(docxParser.getNumbering());
        this.numberings = numberingDefinitionParser.getNumberings();
        this.docxDocument = docxParser.getDocument();
        this.sectionsProperties = docxParser.getSectionsProperties();
        this.paragraphOnNewPageIds = docxParser.getParagraphOnNewPageIds();
        this.paragraphsWithDrawings = docxParser.getDrawingsAndDescriptions();

        this.difference = getDocumentDifference();
    }

    Difference getDocumentDifference() throws Exception {
        Difference difference = new Difference();

        difference.setFilename(new FilenameDiffer(docxPath, config.getFilename()).getDifference());
        difference.setPages(new PagesCountDiffer(docxDocument.getPages(), config.getPages()).getDifference());
        difference.setHeadings(new HeadingDiffer(headings, configParser.getRequiredHeadings()).getHeadingsDifference());

        runSectionController(difference);
        runDrawingController(difference);
        runFooterController(difference);
        runParagraphController(difference);

        difference.setNumbering(new NumberingDiffer(numberings, config.getNumbering()).getDifference());

        difference.setParagraphsCount(new ParagraphsCountDiffer(this.ParagraphsCount, config.getParagraphsCount()).getDifference());

        if (shouldFix)
            saveNewDocument();

        return difference;
    }

    void runSectionController(Difference difference) {
        for (SectPr sectPr : sectionsProperties) {
            new SectionController(sectPr, docxDocument, difference, config.getSection(), shouldFix).parseSection();
        }
    }

    void runFooterController(Difference difference) throws ParserConfigurationException, IOException, SAXException {
        new FooterController(documentData.getHeadersAndFooters(), config.getFooter(), docxDocument, difference)
                .parseFooter();
    }

    void runDrawingController(Difference difference) throws Docx4JException {
        new DrawingController(
                paragraphsWithDrawings,
                config.getDrawing(),
                difference,
                docxDocument,
                documentData)
                .parseDrawing();
    }

    void runParagraphController(Difference difference) throws Exception {
        int count = 0;
        boolean afterTOC = false;

        for (Object p : documentData.getParagraphs()) {
            if (p instanceof SdtBlock)
                afterTOC = true;

            if (p instanceof P) {
                P par = (P) p;
                if (!par.toString().equals("")) {
                    if (!config.getFindHeadingsByTOC() || afterTOC) {
                        ++count;
                        new ParagraphController(count,
                                par,
                                difference,
                                docxDocument,
                                documentData,
                                config,
                                configStyles,
                                headings,
                                paragraphOnNewPageIds,
                                numberings)
                                .parseParagraph();
                    }
                }
            }
        }

        if (config.getExistenceConfig() != null)
            difference.addExistenceConfig(new ExistenceConfigDifference(config.getExistenceConfig()).getDifferenceArray());

        this.ParagraphsCount = count;

        if (config.getFindHeadingsByTOC() && !afterTOC)
            System.out.println("\nError: Table of Contents not found. Please create or update Table of Contents.\n");
    }

    void saveNewDocument() throws Docx4JException {
        wordMLPackage.save(new File(new File(docxPath).getParent() + "/fixed.docx"));
    }

    public Difference getDifference() {
        return difference;
    }

    public DocxDocument getDocxDocument() {
        return docxDocument;
    }
}
