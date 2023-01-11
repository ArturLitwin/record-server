package com.alt.record.service;

import com.alt.record.model.RecordData;
import com.alt.record.stubData.StubData;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


public class CSVToRecordParserTest {


    @Test
    public void testLoadingAlaAndlaAndEla() throws IOException {
        File csvFile = new File("src/main/resources/test3records.csv");
        InputStream inputStream = new FileInputStream(csvFile);
        Collection<RecordData> result = CSVToRecordParser.parseToRecordCollection(inputStream);
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(StubData.ALA_123));
        Assert.assertTrue(result.contains(StubData.ELA_567));
        Assert.assertTrue(result.contains(StubData.OLA_345));
    }


    @Test
    /*
    Test check if there are many records with that same primary_key, only the last one will be returned in result
    */
    public void testLoadingAla3Times() throws IOException {
        File csvFile = new File("src/main/resources/testAla3times.csv");
        InputStream inputStream = new FileInputStream(csvFile);
        Collection<RecordData> result = CSVToRecordParser.parseToRecordCollection(inputStream);
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains(StubData.ALA_123));
    }

    @Test
    /*
    Test check if there are corrupted records they will not be included into result
    */
    public void testLoadingCorruptedRecords() throws IOException {
        File csvFile = new File("src/main/resources/testCorrupted.csv");
        InputStream inputStream = new FileInputStream(csvFile);
        Collection<RecordData> result = CSVToRecordParser.parseToRecordCollection(inputStream);
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(StubData.ALA_123));
        Assert.assertTrue(result.contains(StubData.OLA_345));
    }



}
