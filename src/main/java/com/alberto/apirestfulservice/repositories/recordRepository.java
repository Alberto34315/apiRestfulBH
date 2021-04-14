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

    @Query(value = "SELECT i.* FROM records WHERE iduser=?1", nativeQuery = true)
    public List<records> getAllRecordsByIdUser(Long code);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM record WHERE id = ?1 RETURNING id", nativeQuery = true)
    public Integer deleteFromRecord(Long code);
}
