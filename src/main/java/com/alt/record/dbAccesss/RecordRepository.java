package com.alt.record.dbAccesss;

import com.alt.record.model.RecordData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<RecordData, String> {

    public Optional<RecordData> findByPrimaryKey(String primaryKey);

    @Transactional
    @Modifying
    public void deleteByPrimaryKey(String primaryKey);

}
