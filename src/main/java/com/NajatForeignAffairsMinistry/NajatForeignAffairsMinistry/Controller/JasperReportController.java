package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Controller;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Services.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Date;

@Component
@RestController
public class JasperReportController {

    @Autowired
    JasperReportService jasperReportService;

    //GET + Response entity: 200 OK
    //exp: localhost:8080/api/reports/usage?startDate=2023-05-01&endDate=2023-05-15
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("api/reports/usage")
    public ResponseEntity<String> generateDataaJasperReport(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
        try {
            String reportPath = jasperReportService.generateDataaJasperReport(startDate, endDate);
            return ResponseEntity.ok().body("Report generated: " + reportPath); //200 ok
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to generate report."); //badRequest 400
        }
    }
}
