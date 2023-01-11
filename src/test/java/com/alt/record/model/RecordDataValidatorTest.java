package com.alt.record.model;


import org.junit.Assert;
import org.junit.Test;

public class RecordDataValidatorTest {

    private static final String PRIMARY_KEY = "ALA_123";
    private static final String NAME = "Alas book";
    private static final String DESC = "Book about Ala and her big bad woolf";
    private static final String UPDATE_TIMESTAMP="2001-04-05T18:54:25";

    private static final String INCORRECT_UPDATE_TIMESTAMP="2001/04/05T18:54:25";

    @Test
    public void testValidationWithTimeStamp() {
        boolean result = RecordDataValidator.validateRecordData(PRIMARY_KEY, NAME, DESC, UPDATE_TIMESTAMP);
        Assert.assertTrue(result);
    }

    @Test
    public void testValidationWithOutTimeStamp() {
        boolean result = RecordDataValidator.validateRecordData(PRIMARY_KEY, NAME, DESC, "");
        Assert.assertTrue(result);
    }

    @Test
    public void testValidationWithOutPrimaryKey() {
        boolean result = RecordDataValidator.validateRecordData("", NAME, DESC, UPDATE_TIMESTAMP);
        Assert.assertFalse(result);
    }

    @Test
    public void testValidationWithIncorrectUpdateTimeStamp() {
        boolean result = RecordDataValidator.validateRecordData(PRIMARY_KEY, NAME, DESC, INCORRECT_UPDATE_TIMESTAMP);
        Assert.assertFalse(result);
    }






}
