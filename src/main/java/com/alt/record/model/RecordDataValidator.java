package com.alt.record.model;

import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RecordDataValidator {

    public static boolean validateRecordData(String primaryKey, String name, String description, String updatedTimeStampAsString) {
        //REQ-01 - c. "PRIMARY_KEY" attribute must be non-blank string
        if (!validateRecordData(primaryKey, name, description)) {
            return false;
        }
        //REQ-01 - d. "UPDATED_TIMESTAMP" attribute if present must be a ISO8601 timestamp string
        if (StringUtils.hasText(updatedTimeStampAsString) && !validateTimeStamp(updatedTimeStampAsString)) {
            return false;
        }

        return true;
    }

    public static boolean validateRecordData(String primaryKey, String name, String description) {
        //REQ-01 - c. "PRIMARY_KEY" attribute must be non-blank string
        if (!StringUtils.hasText(primaryKey)) {
            return false;
        }


        //Question : Should be added validation for name if it is not empty string?

        return true;
    }


    private static  boolean validateTimeStamp(String timeStampAsString){
        try {
            DateTimeFormatter.ISO_DATE_TIME.parse(timeStampAsString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


}
