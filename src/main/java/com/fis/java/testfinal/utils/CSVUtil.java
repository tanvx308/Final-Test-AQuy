package com.fis.java.testfinal.utils;

import com.fis.java.testfinal.service.TransactionService;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CSVUtil {

    public static void writeDataLineByLine(String filePath, List<Object[]> objects)
    {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "Id", "Amount", "Content", "Error Reason", "Status", "Date", "FromAccount", "ToAccount", "Customer Name", "Customer Type" };
            writer.writeNext(header);

            // add data to csv
            objects.stream().forEach(item -> writer.writeNext((String[]) item));
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
           log.error(e.getMessage());
        }
    }
}
