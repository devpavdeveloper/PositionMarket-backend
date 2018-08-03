package by.psu.reporting;

import org.apache.poi.ss.usermodel.*;

public class ExcelStyle {

    private CellStyle generatedExcelStyle;

    private Font generatedFont;

    private void createStyle(Workbook wb){
        this.generatedExcelStyle = wb.createCellStyle();
    }

    public ExcelStyle(int cellFontSize, boolean cellFontBold, HorizontalAlignment horizontalAlignment,
                      Workbook wb){
        this.createStyle(wb);
        //generatedExcelStyle.setAlignment(HorizontalAlignment.forInt((short)horizontalAlignment.ordinal()));
        this.generatedFont = wb.createFont();
        generatedFont.setBold(cellFontBold);
        generatedFont.setFontHeightInPoints((short)cellFontSize);
    }

    public ExcelStyle(int cellFontSize, boolean cellFontBold,
                      IndexedColors cellBackgroundColor,
                      IndexedColors cellForegroundColor, HorizontalAlignment horizontalAlignment,
                      Workbook wb){
        this(cellFontSize, cellFontBold, horizontalAlignment, wb);
        generatedExcelStyle.setFillForegroundColor(cellBackgroundColor.getIndex());
        //generatedExcelStyle.setFillPattern(FillPatternType.forInt(FillPatternType.SOLID_FOREGROUND.ordinal()));
        generatedFont.setColor(cellForegroundColor.getIndex());
    }

    public CellStyle getGeneratedExcelStyle(){
        generatedExcelStyle.setFont(generatedFont);
        return this.generatedExcelStyle;
    }
}

