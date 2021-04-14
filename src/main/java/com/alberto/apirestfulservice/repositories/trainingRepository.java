package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import com.alberto.apirestfulservice.model.training;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface trainingRepository extends JpaRepository<training, Long> {

	@Query(value = "SELECT * FROM training AS i WHERE i.title LIKE %?1%", nativeQuery = true)
	public List<training> getByTitle(String title);

	@Query(value = "SELECT * FROM training AS i WHERE i.title LIKE %?1% AND i.iduser=?2", nativeQuery = true)
	public List<training> getByTitleFromUser(String title, Long code);

        @Query(value = "SELECT i.id,i.title,i.time,i.published,i.iduser FROM training AS i LEFT JOIN friendship ON i.iduser = friendship.friend_id WHERE i.title LIKE %?1% AND i.published=1 And friendship.owner_id = ?2 ", nativeQuery = true)
	public List<training> getTrainingOfFriendsByTitle(String title, Long code);

        
	@Query(value = "SELECT * FROM training AS i WHERE i.iduser=?1", nativeQuery = true)
	public List<training> getAllTrainingsByIdUser(Long code);
        
        @Query(value = "SELECT * FROM training AS i WHERE i.iduser=?1 AND i.published=1", nativeQuery = true)
	public List<training> getAllTrainingsByIdUserIsPublished(Long code);
        
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM training WHERE id = ?1 RETURNING id", nativeQuery = true)
	public Integer deleteFromTraining(Long code);

	@Modifying
	@Transactional
	@Query(value = "delete from listexercise where fk_training=?1 and fk_exercise=?2", nativeQuery = true)
	public Integer deleteFromListExercise(Long idT, Long idE);
}