package by.psu.reporting;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.IOException;
import java.util.*;

public class ExcelBuilder {

    private final int ordersSpacing = 2;

    private static String[] userHeaders = {"Имя","Фамилия","Отчество", "Email",
            "Контактный телефон", "Адрес прописки","Адрес проживания","Дата рождения",
            "Кем выдан паспорт", "Дата выдачи", "Скидка"};
    private static String[] orderHeaders = {"Дата оформления заказа", "Дата исполнения заказа"};

    private static String[] attractionsListHeaders = {"Наименование аттракциона","Способ доставки","Цена","Заказан на"};

    private static String orderedAttractionsMessage = "Заказанные аттракционы";

    private static String orderedAttractionsTotalMessage = "Итого";


    public Workbook buildExcelDocument(List attrs){
        Workbook workbook = null;
        try{
            workbook = createWorkBook(attrs);
        }
        catch(IOException exception){

        }
        return workbook;
    }

    private int createHeaders(Sheet sheet, Workbook wb, int rowOffset){

        CellStyle cellStyle = new ExcelStyle(14, true,
                IndexedColors.AQUA, IndexedColors.BLACK, HorizontalAlignment.CENTER, wb).getGeneratedExcelStyle();
        Row headerRow = sheet.createRow(rowOffset);
        for(int x =0;x<userHeaders.length;x++){
            Cell c = headerRow.createCell(x);
            c.setCellValue(userHeaders[x]);
            c.setCellStyle(cellStyle);
            sheet.autoSizeColumn(x);
        }
        return ++rowOffset;
    }

   /* private int createProductHeaders(Sheet sheet, Workbook wb, int rowOffset, OrderIssue orderIssue){

        CellStyle cellStyleBottomCreated = new ExcelStyle(14, true,
                IndexedColors.GOLD, IndexedColors.BLACK, HorizontalAlignment.CENTER, wb).getGeneratedExcelStyle();
        CellStyle cellStyleBottomDone = new ExcelStyle(14, true,
                IndexedColors.LAVENDER, IndexedColors.BLACK, HorizontalAlignment.CENTER, wb).getGeneratedExcelStyle();

        Row headerRowBottom = sheet.createRow(rowOffset);
        for(int x = 0;x<orderHeaders.length;x++){
            Cell cell = headerRowBottom.createCell(x);
            cell.setCellValue(orderHeaders[x]);
            switch(x){
                case 0:{
                    cell.setCellStyle(cellStyleBottomCreated);
                    break;
                }
                case 1:{
                    cell.setCellStyle(cellStyleBottomDone);
                    break;
                }
            }
            sheet.autoSizeColumn(x, true);
        }
        return ++rowOffset;
    }*/
/*

    private int createOrderInfoCells(Sheet sheet, Workbook wb, int rowOffset, OrderIssue order){
        Row headerRow = sheet.createRow(rowOffset);
        CellStyle cellStyle = new ExcelStyle(13, true, HorizontalAlignment.LEFT, wb).getGeneratedExcelStyle();
        for(int x = 0;x<orderHeaders.length;x++){
            Cell c = headerRow.createCell(x);
            c.setCellStyle(cellStyle);
            Date date = null;
            if(x!=0){
                c.setCellValue(Util.convertDateToReadableLocalFormat(order.getDateOrder()));
            }
            else{
                c.setCellValue(Util.convertDateToReadableLocalFormat(order.getDateEvent()));
            }
        }
        return ++rowOffset;
    }
*/

    private int createOrderAttractionsMessage(Sheet sheet, Workbook wb, int rowOffset){

        CellStyle cellStyle = new ExcelStyle(14, true,
                IndexedColors.ORANGE, IndexedColors.BLACK, HorizontalAlignment.CENTER, wb).getGeneratedExcelStyle();
        Row headerRowTop = sheet.createRow(rowOffset);
        Cell c = headerRowTop.createCell(0);
        c.setCellValue(orderedAttractionsMessage);
        c.setCellStyle(cellStyle);
        sheet.autoSizeColumn(0, true);
        return ++rowOffset;
    }

    private int createOrderAttractionsHeaders(Sheet sheet, Workbook wb, int rowOffset){
        CellStyle cellStyle = new ExcelStyle(14, true,
                IndexedColors.LIGHT_YELLOW, IndexedColors.BLACK, HorizontalAlignment.CENTER, wb).getGeneratedExcelStyle();
        Row headerRow = sheet.createRow(rowOffset);
        for(int x = 0;x<attractionsListHeaders.length;x++){
            Cell c = headerRow.createCell(x);
            c.setCellValue(attractionsListHeaders[x]);
            c.setCellStyle(cellStyle);
        }
        return ++rowOffset;
    }

    private int createTotals(Sheet sheet, Workbook wb, int rowOffset, float total){
        CellStyle cellStyle = new ExcelStyle(14, true,
                IndexedColors.ROSE, IndexedColors.BLACK, HorizontalAlignment.CENTER, wb).getGeneratedExcelStyle();
        Row row = sheet.createRow(rowOffset);

        for(int x = 0;x<attractionsListHeaders.length;x++){
            Cell c = row.createCell(x);
            c.setCellStyle(cellStyle);
            switch(x){
                case 0:{
                    c.setCellValue(orderedAttractionsTotalMessage);
                    break;
                }
                case 2:{
                    c.setCellValue(total);
                    break;
                }
                default:{
                    c.setCellValue("");
                }
            }
        }
        return ++rowOffset;
    }

    private Workbook createWorkBook(List orders) throws IOException{

        int rowOffset = 0;
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Orders");

        /*//user part
        CellStyle ordinaryTextStyle = new ExcelStyle(13, true, HorizontalAlignment.LEFT,
                wb).getGeneratedExcelStyle();

        for(int ordersCount = 0;ordersCount<orders.size();ordersCount++){
            rowOffset = createHeaders(sheet, wb, rowOffset);
            Row r = sheet.createRow(rowOffset);
            User clientBasicInfo = orders.get(ordersCount).getUser();
            InfoUser clientDetailedInfo = clientBasicInfo.getInfoUser();
            for(int cn=0;cn<11;cn++){

                Cell cell =  r.createCell(cn);
                cell.setCellStyle(ordinaryTextStyle);
                switch(cn){
                    case 0:{
                        cell.setCellValue(clientDetailedInfo.firstname);
                        break;
                    }
                    case 1:{
                        cell.setCellValue(clientDetailedInfo.lastname);
                        break;
                    }
                    case 2:{
                        cell.setCellValue(clientDetailedInfo.patronymic);
                        break;
                    }
                    case 3:{
                        cell.setCellValue(clientBasicInfo.getEmail());
                        break;
                    }
                    case 4:{
                        cell.setCellValue(clientBasicInfo.getPhone());
                        break;
                    }
                    case 5:{
                        cell.setCellValue(clientDetailedInfo.regAddress);
                        break;
                    }
                    case 6:{
                        cell.setCellValue(clientDetailedInfo.residence);
                        break;
                    }
                    case 7:{
                        cell.setCellValue(Util.convertDateToReadableLocalFormat(clientDetailedInfo.birthday));
                        break;
                    }
                    case 8:{
                        cell.setCellValue(clientDetailedInfo.issueAuthPassport);
                        break;
                    }
                    case 9:{
                        cell.setCellValue(Util.convertDateToReadableLocalFormat(clientDetailedInfo.dateIssue));
                        break;
                    }
                    case 10:{
                        cell.setCellValue(clientBasicInfo.getDiscount());
                    }

                }
            }
            rowOffset++;
            rowOffset = createProductHeaders(sheet, wb, rowOffset, orders.get(ordersCount));
            //products part
            rowOffset = createOrderInfoCells(sheet, wb, rowOffset, orders.get(ordersCount));
            rowOffset = createOrderAttractionsMessage(sheet, wb, rowOffset);
            rowOffset = createOrderAttractionsHeaders(sheet, wb, rowOffset);
            //create
            Set<Product> orderedProducts = orders.get(ordersCount).getProducts();
            CellStyle listCellStyle = new ExcelStyle(13, false, HorizontalAlignment.LEFT, wb).getGeneratedExcelStyle();
            for(Product product:orderedProducts){
                Row currentAttractionRow = sheet.createRow(rowOffset);
                Attraction orderedAttraction = product.getAttraction();

                for(int x = 0;x<4;x++){
                    Cell c = currentAttractionRow.createCell(x);
                    c.setCellStyle(listCellStyle);
                    switch(x){
                        case 0:{
                            c.setCellValue(orderedAttraction.getTitle());
                            break;
                        }
                        case 1:{
                            c.setCellValue(product.getTypePrice().getTitle());
                            break;
                        }
                        case 2:{
                            c.setCellValue(product.getPrice());
                            break;
                        }
                        case 3:{
                            c.setCellValue(Util.convertDateToReadableLocalFormat(orders.get(ordersCount).getDateOrder()));
                            break;
                        }
                    }
                }
                rowOffset++;
            }
            rowOffset = createTotals(sheet,wb,rowOffset, 123.0F);
            rowOffset+=ordersSpacing;
        }
*/
        return wb;
    }
}

