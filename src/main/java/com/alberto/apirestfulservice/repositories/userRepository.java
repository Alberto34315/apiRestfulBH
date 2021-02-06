package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;

import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

	@Query(value = "SELECT * FROM users AS i WHERE i.name LIKE %?1%", nativeQuery = true)
	public List<user> getByTitle(String title);
}