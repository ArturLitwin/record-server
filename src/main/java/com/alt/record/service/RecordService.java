package com.alt.record.service;

import com.alt.record.dbAccesss.RecordRepository;
import com.alt.record.model.RecordData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    /*
    Method returns count of parsed records. So this is basic information for client if all records in file were
    valid. But this is very basic solution. To discuss what are needs in that situation.
     */
    public int save(MultipartFile file) {
        try {
            Collection<RecordData> recordDataList = CSVToRecordParser.parseToRecordCollection(file.getInputStream());
            recordRepository.saveAll(recordDataList);
            return recordDataList.size();
        } catch (IOException e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    /*
    Method return Optional<RecordData> but maybe it should return Optional<RecorDataWithoutEntityDependencies>
    To discuss
     */
    public Optional<RecordData> findByPrimaryKey(String recordId) {
        return recordRepository.findByPrimaryKey(recordId);
    }

    public void deleteByPrimaryKey(String recordId) {
        recordRepository.deleteByPrimaryKey(recordId);
    }


}
