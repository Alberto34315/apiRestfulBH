package com.alberto.apirestfulservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;
import com.alberto.apirestfulservice.repositories.userRepository;

@Service
public class userService {

    @Autowired
    userRepository repository;

    public List<user> getAllUsers() {
        List<user> itemList = repository.findAll();

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public List<user> getAllUserLessOwner(Long owner_id1, Long owner_id2,Long num) {
        List<user> itemList = repository.getAllUserLessOwner(owner_id1, owner_id2,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public List<user> searchUserLessOwner(Long owner_id1, Long owner_id2, String name,Long num) {
        List<user> itemList = repository.searchUserLessOwner(owner_id1, owner_id2, name,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public List<user> getAllFriends(Long owner_id) {
        List<user> itemList = repository.getAllFriends(owner_id);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public List<user> getAllFriendsLimit(Long owner_id,Long num) {
        List<user> itemList = repository.getAllFriendsLimit(owner_id,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }
    
    public List<user> searchFriends(Long owner_id, String name,Long num) {
        List<user> itemList = repository.searchFriends(owner_id, name,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public user getUserById(Long id) throws RecordNotFoundException {
        Optional<user> item = repository.findById(id);

        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public List<user> getItemsByName(String title) {
        List<user> itemList = repository.getByTitle(title);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public user searchCredentials(String email, String pass) {
        user item = repository.searchCredentials(email, pass);

        if (item != null) {
            return item;
        } else {
            return new user();
        }
    }

    public user searchEmail(String email) {
        user item = repository.searchEmail(email);

        if (item != null) {
            return item;
        } else {
            return new user();
        }
    }

    public List<user> getAllUsersByIdTrainingFavorite(Long id) {
        List<user> itemList = repository.getAllUsersByIdTrainingFavorite(id);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<user>();
        }
    }

    public user createUser(user entity) {
        entity = repository.save(entity);
        return entity;
    }

    public user UpdateUser(user entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<user> item = repository.findById(entity.getId());

            if (item.isPresent()) {
                user newEntity = item.get();
                // newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setEmail(entity.getEmail());
                newEntity.setPass(entity.getPass());
                newEntity.setAvatar(entity.getAvatar());
                newEntity.setLt(entity.getLt());
                newEntity.setLe(entity.getLe());
                newEntity.setFriends(entity.getFriends());
                newEntity.setLrecords(entity.getLrecords());
                newEntity.setPrivateCount(entity.isPrivateCount());
                newEntity.setTrainingsf(entity.getTrainingsf());
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of item given", 0l);
        }
    }

    public void deleteUserById(Long id) throws RecordNotFoundException {
        Optional<user> item = repository.findById(id);

        if (item.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public void deleteFromFriendship(Long owner_id, Long friend_id) throws RecordNotFoundException {
        Optional<user> owner = repository.findById(owner_id);
        Optional<user> friend = repository.findById(friend_id);
        if (owner.isPresent() && friend.isPresent()) {
            repository.deleteFromFriendship(owner_id, friend_id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", owner_id);
        }
    }
}
