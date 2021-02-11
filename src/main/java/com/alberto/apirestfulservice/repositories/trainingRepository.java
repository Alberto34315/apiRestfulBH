package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import com.alberto.apirestfulservice.model.training;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface trainingRepository extends JpaRepository<training, Long> {
	
	@Query(value="SELECT * FROM training AS i WHERE i.title LIKE %?1%",
		            nativeQuery=true)
		    public List<training> getByTitle(String title);
		    
   @Query(value = "DELETE FROM training AS i WHERE i.id = ?1 RETURNING id", nativeQuery = true)
		    public Long deleteFromTraining (Long code);
}