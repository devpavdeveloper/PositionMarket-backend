package by.psu.controllers;

import by.psu.reporting.ExcelBuilder;
import by.psu.service.ReportingService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportingService reportingService;

    private final ExcelBuilder excelBuilder;

    @Autowired
    public ReportController(ReportingService reportingService, ExcelBuilder excelBuilder) {
        this.reportingService = reportingService;
        this.excelBuilder = excelBuilder;
    }


    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> download(HttpServletResponse response) throws Exception {
       /* List<OrderIssue> attrs = reportingService.getAllOrders();
        response.setHeader("Content-disposition",
                "inline; filename=file.xlsx");
        response.setContentType("application/vnd.ms-excel");
        Workbook excelDocument = excelBuilder.buildExcelDocument(attrs);
        excelDocument.write(response.getOutputStream());*/
        return ResponseEntity.ok().body(response);
    }
}

