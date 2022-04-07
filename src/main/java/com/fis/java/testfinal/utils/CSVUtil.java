package com.fis.java.testfinal.utils;

import com.fis.java.testfinal.service.TransactionService;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CSVUtil {

    public static void writeDataLineByLine(String filePath, List<Object[]> objects)
    {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "Transaction", "Customer Name", "Customer Type" };
            writer.writeNext(header);

            // add data to csv
            for (Object[] objArr: objects){
                String[] strArr = Arrays.stream(objArr).map(Object::toString).toArray(String[]::new);
                writer.writeNext(strArr);
            }
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
           log.error(e.getMessage());
        }
    }
}
