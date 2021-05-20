package com.alberto.apirestfulservice.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

    @Query(value = "SELECT * FROM users AS i WHERE i.name LIKE %?1%", nativeQuery = true)
    public List<user> getByTitle(String title);

    @Query(value = "select id,name,email,pass,avatar,private_count from users where email like ?1 and  pass like ?2", nativeQuery = true)
    public user searchCredentials(String email, String pass);

    @Query(value = "select id,name,email,pass,avatar,private_count from users where email like ?1", nativeQuery = true)
    public user searchEmail(String email);
    //INSERT INTO friendship (owner_id, friend_id) VALUES (1,2);

    /*SELECT * from users LEFT JOIN friendship ON users.id = friendship.friend_id WHERE friendship.owner_id = 2 UNION
          SELECT * FROM users LEFT JOIN friendship ON users.id  = friendship.owner_id WHERE friendship.friend_id = 2*/
    /**
     * Devuelve los amigos de un usuario SELECT * from users LEFT JOIN
     * friendship ON users.id = friendship.friend_id WHERE friendship.owner_id =
     * ?1
     * 
     */
    /**
     * https://stackoverflow.com/questions/20533441/sql-request-display-people-who-are-not-friend
     * Devuelve los usuarios que no son amigos de un usuario SELECT * FROM users
     * LEFT JOIN friendship ON (users.id=friendship.friend_id AND
     * friendship.owner_id = 1) WHERE friendship.friend_id IS NULL AND users.id
     * != 1;
     */
    // @Query(value = "SELECT id,name,email,pass,avatar from users LEFT JOIN friendship ON users.id = friendship.friend_id WHERE friendship.owner_id != ?1 AND users.id!=?2 AND users.name LIKE %?3%", nativeQuery = true)
    @Query(value = "SELECT id,name,email,pass,avatar,private_count from users LEFT JOIN friendship ON (users.id=friendship.friend_id AND friendship.owner_id = ?1) WHERE friendship.friend_id IS NULL AND users.id != ?2 AND private_count=0 LIMIT ?3,10", nativeQuery = true)
    public List<user> getAllUserLessOwner(Long owner_id1, Long owner_id2,Long num);

    @Query(value = "SELECT id,name,email,pass,avatar,private_count from users LEFT JOIN friendship ON (users.id=friendship.friend_id AND friendship.owner_id = ?1) WHERE friendship.friend_id IS NULL AND users.id != ?2 AND private_count=0 AND users.name LIKE %?3% LIMIT ?4,10", nativeQuery = true)
    public List<user> searchUserLessOwner(Long owner_id1, Long owner_id2, String name,Long num);

    //@Query(value = "SELECT id,name,email,pass,avatar from users LEFT JOIN friendship ON users.id = friendship.friend_id WHERE friendship.owner_id = ?1 AND users.name LIKE %?2%", nativeQuery = true)
    @Query(value = "SELECT id,name,email,pass,avatar,private_count from users LEFT JOIN friendship ON users.id = friendship.friend_id WHERE friendship.owner_id = ?1", nativeQuery = true)
    public List<user> getAllFriends(Long owner_id);

    @Query(value = "SELECT id,name,email,pass,avatar,private_count from users LEFT JOIN friendship ON users.id = friendship.friend_id WHERE friendship.owner_id = ?1 LIMIT ?2,10", nativeQuery = true)
    public List<user> getAllFriendsLimit(Long owner_id,Long num);
    
    @Query(value = "SELECT id,name,email,pass,avatar,private_count from users LEFT JOIN friendship ON users.id = friendship.friend_id WHERE friendship.owner_id = ?1 AND users.name LIKE %?2% LIMIT ?3,10", nativeQuery = true)
    public List<user> searchFriends(Long owner_id, String name,Long num);

    @Query(value = "SELECT users.id,users.name,users.email,users.pass,users.avatar,users.private_count FROM users LEFT JOIN favorites ON users.id = favorites.fk_user WHERE favorites.fk_trainings=?1", nativeQuery = true)
    public List<user> getAllUsersByIdTrainingFavorite(Long code);

    @Modifying
    @Transactional
    @Query(value = "delete from friendship where owner_id=?1 and friend_id=?2", nativeQuery = true)
    public Integer deleteFromFriendship(Long owner_id, Long friend_id);
}
