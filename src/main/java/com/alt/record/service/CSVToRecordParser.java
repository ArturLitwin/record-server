package com.alt.record.service;

import com.alt.record.model.RecordData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alt.record.model.RecordDataValidator;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CSVToRecordParser {

    private static Logger logger = LoggerFactory.getLogger(CSVToRecordParser.class);

    private CSVToRecordParser() {};

    public static Collection<RecordData> parseToRecordCollection(InputStream is) {

        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(
                        fileReader,
                        CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build()
                );
        ) {
            Iterable<CSVRecord> csvRecordList = csvParser.getRecords();
            /*
            I assume that primary key must be unique, so if parsed file contains many records with that same
            primary key field, the last one will be stored in result
            */
            Map<String, RecordData> resultAsMap = new HashMap<>();
            for (CSVRecord csvRecord : csvRecordList) {
                String primaryKey = csvRecord.get("PRIMARY_KEY");
                String name = csvRecord.get("NAME");
                String description = csvRecord.get("DESCRIPTION");
                String updatedTimeStampAsString = csvRecord.get("UPDATED_TIMESTAMP");
                //REQ-04: As service owner I want no invalid records from uploaded CSV file to be saved
                if (RecordDataValidator.validateRecordData(primaryKey, name, description, updatedTimeStampAsString)) {
                    LocalDateTime updatedTimeStamp = null;
                    if (StringUtils.hasText(updatedTimeStampAsString)) {
                        updatedTimeStamp = LocalDateTime.parse(updatedTimeStampAsString, DateTimeFormatter.ISO_DATE_TIME);
                    }
                    RecordData recordData = RecordData.of(primaryKey, name, description, updatedTimeStamp);
                    resultAsMap.put(recordData.getPrimaryKey(), recordData);
                } else {
                    /*
                    Question: What to do with invalid records? Should be client informed somehow about this?
                    For now as very basic solution adding warnig to log file
                     */
                    logger.warn("Incorrect record if CSV file " + csvRecord);
                }
            }
            return resultAsMap.values();
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file :  " + e.getMessage());
        }
    }

}
