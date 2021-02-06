package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;

import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface exerciseRepository extends JpaRepository<exercise, Long> {

	@Query(value = "SELECT * FROM exercise AS i WHERE i.nameExercise LIKE %?1%", nativeQuery = true)
	public List<exercise> getByTitle(String title);

}