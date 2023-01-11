package com.alt.record.model;


import javax.persistence.*;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "STANDARD_RECORD")
public class RecordData {


    /*
    I decided to use natural key (primary key) as DB record ID because if record with specified primary_key already exist
    in table, and record with specified primary key is loaded from csv file again it should be updated. ALtenative
    to this is implement regular DB id, but in that situation before saving record from CSV file there hat do be
    check if file with specified primary key already exist in DB, if yes, it should be loaed from base and merged
    with new data from csf file, and thad saved. But this solution is to discuss.
     */
    @Id
    @Column(name = "primary_key", nullable = false, unique = true)
    private String primaryKey;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimeStamp;

    private RecordData() {}


    public static RecordData of(String primaryKey, String name, String description, LocalDateTime updatedTimeStamp) {
        RecordData recordData = new RecordData();
        //Validation is repeated here to avoid situation when inconsistent record is created
        if (!RecordDataValidator.validateRecordData(primaryKey, name, description)) {
            throw new InvalidParameterException(primaryKey + " " + name);
        }
        recordData.primaryKey = primaryKey;
        recordData.name = name;
        recordData.description = description;
        recordData.updatedTimeStamp = updatedTimeStamp;

        return recordData;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordData that = (RecordData) o;
        return Objects.equals(primaryKey, that.primaryKey) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(updatedTimeStamp, that.updatedTimeStamp
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey, name, description, updatedTimeStamp);
    }

    @Override
    public String toString() {
        return "RecordData{" +
                "primaryKey='" + primaryKey + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", updatedTimeStamp=" + updatedTimeStamp +
                '}';
    }
}
