/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alberto.apirestfulservice.repositories;

import com.alberto.apirestfulservice.model.records;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface recordRepository extends JpaRepository<records, Long> {

    @Query(value = "SELECT id,(date+INTERVAL 2 HOUR) as date ,fk_idtrainig,iduser FROM records WHERE iduser=?1 ORDER BY DATE ASC LIMIT ?2,10", nativeQuery = true)
    public List<records> getAllRecordsByIdUser(Long code,Long num);

    @Query(value = "SELECT COUNT(*) from records WHERE (DATE+INTERVAL 2 HOUR) LIKE ?1 AND iduser=?2", nativeQuery = true)
    public Integer getNumberOfTrainingsForDate(String time,Long code);

    @Query(value = "SELECT id,(DATE(records.date+INTERVAL 2 HOUR)) as date ,fk_idtrainig,iduser FROM records where iduser=?1 GROUP BY (DATE(records.date+INTERVAL 2 HOUR)) ORDER BY records.date DESC LIMIT 7", nativeQuery = true)
    public List<records> getLastSevenRecordsByIdUser(Long code);

     @Query(value = "SELECT records.id,(records.date+INTERVAL 2 HOUR) as date ,records.fk_idtrainig,records.iduser FROM records LEFT JOIN training ON records.fk_idtrainig=training.id WHERE records.iduser=?1 AND training.title LIKE %?2%  LIMIT ?3,10", nativeQuery = true)
    public List<records> searchRecord(Long code, String name,Long num);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE id = ?1 RETURNING id", nativeQuery = true)
    public Integer deleteFromRecord(Long code);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE fk_idtrainig = ?1 RETURNING id", nativeQuery = true)
    public Integer deleteTrainingFromRecord(Long code);
}
