package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

import java.util.List;
import java.util.Set;

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

    @Query(value = "SELECT COUNT(*) FROM favorites WHERE FK_TRAININGS=?1 AND fk_user=?2", nativeQuery = true)
    public Integer isTrainingFavorite(Long code1, Long code2);

    @Query(value = "SELECT training.id,training.title,training.time,training.published,training.iduser FROM training LEFT JOIN favorites ON training.id = favorites.fk_trainings WHERE favorites.fk_user=?1", nativeQuery = true)
    public List<training> getAllTrainingsFromFavorites(Long code);

    @Query(value = "SELECT training.id,training.title,training.time,training.published,training.iduser FROM training LEFT JOIN favorites ON training.id = favorites.fk_trainings WHERE favorites.fk_user=?1 AND training.title LIKE %?2%", nativeQuery = true)
    public List<training> searchTrainingsFromFavorites(Long code,String title);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO favorites (FK_TRAININGS, fk_user) VALUES (?1,?2)", nativeQuery = true)
    public void insertTrainingFavorite(Long code1, Long code2);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM favorites WHERE FK_TRAININGS = ?1 and fk_user = ?2", nativeQuery = true)
    public Integer deleteTrainingFavorite(Long code1, Long code2);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM favorites WHERE FK_TRAININGS = ?1 ", nativeQuery = true)
    public Integer deleteAllTrainingsFavorite(Long code1);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM training WHERE id = ?1 RETURNING id", nativeQuery = true)
    public Integer deleteFromTraining(Long code);

    @Modifying
    @Transactional
    @Query(value = "delete from listexercise where fk_training=?1 and fk_exercise=?2", nativeQuery = true)
    public Integer deleteFromListExercise(Long idT, Long idE);
}
