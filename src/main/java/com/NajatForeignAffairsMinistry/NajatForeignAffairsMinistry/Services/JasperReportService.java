package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Services;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.DTO.DataaDTO;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.Dataa;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories.DataaRepository;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories.NewsRepository;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories.PoliciesRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class JasperReportService {

    @Autowired
    DataaRepository dataaRepository;
    @Autowired
    PoliciesRepository policiesRepository;
    @Autowired
    NewsRepository newsRepository;

    public static final String pathToReports = "C:\\Users\\Acer\\intellijIdea-workspace\\NajatForeignAffairsMinistry\\JasperReports";

    /* Here you should write URL in postman with startDate and endDate as input. then from that date
    will sum all (totalRequests), and (totalErrors), and (totalResponseTime), then will
    appear in jasper report as a result of total of each column.
     */
    //findByStartDateAndEndDate
    public String generateDataaJasperReport(Date startDate, Date endDate) throws FileNotFoundException, JRException {
        List<Dataa> dataaList = dataaRepository.findByStartDateAndEndDate(startDate, endDate);
        List<DataaDTO> dataaDTOList = new ArrayList<>(); //for jasper report list.

        //Calculate the totalRequests, totalErrors, and responseTime.
        int totalRequests = 0; //equal to zero because of sum.
        int totalErrors = 0;
        int totalResponseTime = 0;

        //take values from db of each column and add them
        for (Dataa dataa : dataaList) {
            totalRequests += dataa.getTotalRequests();
            totalErrors += dataa.getTotalErrors();
            totalResponseTime += dataa.getResponseTime();
        }

        // define object of DataaDTO with the calculated values, then add them to jasper report as list.
        DataaDTO dataaDTO = new DataaDTO(totalRequests, totalErrors, totalResponseTime);
        dataaDTOList.add(dataaDTO);

        File file = ResourceUtils.getFile("classpath:DataaReport_jaspersoft.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataaDTOList);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "Najat Tech Mahindra");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,paramters , dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports+"\\DataaJasperReport.pdf");
        return "Report generated : " + pathToReports+"\\DataaJasperReport.pdf";
    }
}
