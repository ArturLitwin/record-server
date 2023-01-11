package com.alt.record.stubData;

import com.alt.record.model.RecordData;

import java.time.LocalDateTime;

public class StubData {

    public static final RecordData ALA_123;
    public static final RecordData OLA_345;
    public static final RecordData ELA_567;


    static {
        ALA_123 = RecordData.of("ALA_123","ALA", "Story about Ala", LocalDateTime.parse("2021-07-08T15:30:08"));
        OLA_345 = RecordData.of("OLA_345","OLA", "Story about Ola", null);
        ELA_567 = RecordData.of("ELA_567","ELA", "Story about Ela", LocalDateTime.parse("2021-07-08T14:30:08"));
    }

}
