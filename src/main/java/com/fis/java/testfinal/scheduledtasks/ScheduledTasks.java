package com.fis.java.testfinal.scheduledtasks;

import com.fis.java.testfinal.service.TransactionService;
import com.fis.java.testfinal.utils.CSVUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {
    @Autowired
    TransactionService transactionService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleTaskWithCronExpression() {
        LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<Object[]> objects = transactionService.reportByDay(yesterday.format(formatter));
        String filePath = "transaction_" + yesterday.format(dateTimeFormatter) + ".csv";
        CSVUtil.writeDataLineByLine(filePath, objects);
        log.info("Export file: " + filePath);
    }
}
