package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface exerciseRepository extends JpaRepository<exercise, Long> {

    @Query(value = "SELECT * FROM exercise AS i WHERE i.name_exercise LIKE %?1%", nativeQuery = true)
    public List<exercise> getByTitle(String title);

    @Query(value = "SELECT * FROM exercise AS i WHERE i.name_exercise LIKE %?1% AND i.iduser=?2 LIMIT ?3,10", nativeQuery = true)
    public List<exercise> getByTitleFromUser(String title, Long code, Long num);

    @Query(value = "SELECT * FROM exercise AS i WHERE i.iduser=?1 LIMIT ?2,10", nativeQuery = true)
    public List<exercise> getAllExercisesByIdUser(Long code, Long num);

    @Query(value = "SELECT DISTINCT i.* FROM exercise AS i LEFT JOIN listexercise ON i.id != listexercise.fk_exercise where i.iduser=?1 AND i.id NOT IN (SELECT listexercise.fk_exercise FROM listexercise) LIMIT ?2,10", nativeQuery = true)
    public List<exercise> getAllExercisesByIdUserAndNotFoundTraining(Long code1, Long num);
    
    @Query(value = "SELECT DISTINCT i.* FROM exercise AS i LEFT JOIN listexercise ON i.id != listexercise.fk_exercise where i.iduser=?1 AND i.id NOT IN (SELECT listexercise.fk_exercise FROM listexercise) AND i.name_exercise LIKE %?2% LIMIT ?3,10", nativeQuery = true)
    public List<exercise> searchAllExercisesByIdUserAndNotFoundTraining(Long code1, String title, Long num);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM exercise WHERE id = ?1 RETURNING id", nativeQuery = true)
    public Integer deleteFromExercise(Long code);

}
