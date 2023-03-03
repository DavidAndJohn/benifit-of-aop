package com.demo.service;

import com.demo.dao.ReportDao;
import com.demo.ds.Report;
import com.demo.format.ReportFormatter;
import com.demo.logger.PerformanceLogger;
import com.demo.repository.ReportProvider;
import org.springframework.stereotype.Service;
import static com.demo.logger.PerformanceLogger.*;

@Service
public class ComplexAction {

    private ReportDao reportDao;
    private ReportProvider reportProvider;
    private ReportFormatter reportFormatter;

    public ComplexAction(ReportDao reportDao, ReportProvider reportProvider, ReportFormatter reportFormatter) {
        this.reportDao = reportDao;
        this.reportProvider = reportProvider;
        this.reportFormatter = reportFormatter;
    }

    public void takeAction(){
        PerformanceLogger logger= new PerformanceLogger();

        PerformanceInfo performanceInfo=logger
                .begin("ReportFormatter::formatReport(new Report())");

        Report formattedReport=reportFormatter
                .formatReport(new Report());

        performanceInfo=logger
                .begin("Report Dao::save(formattedReport)");

        reportDao.save(formattedReport);
        logger.end(performanceInfo);

        performanceInfo=logger
                .begin("ReportProvider::reportProvider");
        Report report=reportProvider.reportProvide();
    }



}
