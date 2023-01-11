package com.alt.record.controller;

import com.alt.record.model.RecordData;
import com.alt.record.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Optional;



@RestController
@RequestMapping("records")
public class CSVController {

    private final RecordService recordService;

    private Logger logger = LoggerFactory.getLogger(CSVController.class);

    public CSVController(RecordService recordService) {
        this.recordService = recordService;
    }



    @PostMapping("/upload")
    public int uploadCSVFile(@RequestParam("file") MultipartFile file) {
        logger.info("Uploading file  : " + file.getResource().getFilename());
        int recordsCount = recordService.save(file);
        logger.info("File uploaded. Number of records parsed : " + recordsCount);
        return recordsCount;
    }


    @GetMapping("/{primaryKey}")
    public Optional<RecordData> findByPrimaryKey(@PathVariable String primaryKey) {
        logger.info("Finding record : " + primaryKey);
        Optional<RecordData> recordData = recordService.findByPrimaryKey(primaryKey);
        logger.info("Finding record result : " + recordData);
        return recordData;
    }

    @DeleteMapping("/{primaryKey}")
    public void deleteByPrimaryKey(@PathVariable String primaryKey) {
        logger.info("Deleting record : " + primaryKey);
        recordService.deleteByPrimaryKey(primaryKey);
    }

}
