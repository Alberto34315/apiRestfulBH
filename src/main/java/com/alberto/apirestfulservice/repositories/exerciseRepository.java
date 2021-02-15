package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;

import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface exerciseRepository extends JpaRepository<exercise, Long> {

	@Query(value="SELECT * FROM exercise AS i WHERE i.nameExercise LIKE %?1%", nativeQuery=true)
    public List<exercise> getByTitle(String title);
    
	@Query(value="SELECT * FROM exercise AS i WHERE i.nameExercise LIKE %?1% AND i.iduser=?2", nativeQuery=true)
    public List<exercise> getByTitleFromUser(String title,Long code);
    
    @Query(value="SELECT * FROM exercise AS i WHERE i.iduser=?1", nativeQuery=true)
    public List<exercise> getAllTrainingsByIdUser(Long code);
    
	@Query(value = "DELETE FROM exercise AS i WHERE i.id = ?1 RETURNING id", nativeQuery = true)
    public Long deleteFromExercise (Long code);

}