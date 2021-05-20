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
import com.alberto.apirestfulservice.repositories.exerciseRepository;
import com.alberto.apirestfulservice.repositories.trainingRepository;
import com.alberto.apirestfulservice.repositories.userRepository;
import java.util.Set;

@Service
public class trainingService {

    @Autowired
    trainingRepository repository;

    @Autowired
    exerciseRepository exerR;

    @Autowired
    userRepository userR;

    public List<training> getAllTrainings() {
        List<training> itemList = repository.findAll();

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public List<training> getAllTrainingsByIdUser(Long id,Long num) {
        List<training> itemList = repository.getAllTrainingsByIdUser(id,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public List<training> getAllTrainingsFromFavorites(Long id,Long num) {
        List<training> itemList = repository.getAllTrainingsFromFavorites(id,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public List<training> getAllTrainingsByIdUserIsPublished(Long id,Long num) {
        List<training> itemList = repository.getAllTrainingsByIdUserIsPublished(id,num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public training getTrainingById(Long id) throws RecordNotFoundException {
        Optional<training> item = repository.findById(id);

        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public List<training> getItemsByTitle(String title) {
        List<training> itemList = repository.getByTitle(title);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public List<training> getByTitleFromUser(String title, Long code,Long num) {
        List<training> itemList = repository.getByTitleFromUser(title, code, num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public List<training> searchTrainingsFromFavorites(Long code, String title,Long num) {
        List<training> itemList = repository.searchTrainingsFromFavorites(code, title, num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public List<training> getTrainingOfFriendsByTitle(String title, Long code,Long num) {
        List<training> itemList = repository.getTrainingOfFriendsByTitle(title, code, num);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<training>();
        }
    }

    public Integer isTrainingFavorite(Long code1, Long code2) {
        Integer itemList = repository.isTrainingFavorite(code1, code2);
        if (itemList > 0) {
            return itemList;
        } else {
            return 0;
        }
    }

    public void insertTrainingFavorite(Long code1, Long code2) {
        repository.insertTrainingFavorite(code1, code2);
    }

    public training createTraining(training entity) {
        //System.out.println(myItem);
        entity = repository.save(entity);
        return entity;
    }

    public training UpdateTraining(training entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<training> item = repository.findById(entity.getId());

            if (item.isPresent()) {
                training newEntity = item.get();
                // newEntity.setId(entity.getId());
                newEntity.setTitle(entity.getTitle());
                newEntity.setExercises(entity.getExercises());
                newEntity.setCreator(entity.getCreator());
                newEntity.setTime(entity.getTime());
                newEntity.setLr(entity.getLr());
                newEntity.setPublished(entity.isPublished());
                newEntity.setUsersf(entity.getUsersf());
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of item given", 0l);
        }
    }

    public void deleteTrainingById(Long id) throws RecordNotFoundException {
        Optional<training> item = repository.findById(id);

        if (item.isPresent()) {
            repository.deleteFromTraining(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public void deleteFromExercise(Long idT, Long idE) throws RecordNotFoundException {
        Optional<training> itemT = repository.findById(idT);
        Optional<exercise> itemE = exerR.findById(idE);
        if (itemT.isPresent() && itemE.isPresent()) {
            repository.deleteFromListExercise(idT, idE);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", idT);
        }
    }

    public void deleteTrainingFavorite(Long code1, Long code2) throws RecordNotFoundException {
        Optional<training> itemT = repository.findById(code1);
        Optional<user> itemU = userR.findById(code2);
        if (itemT.isPresent() && itemU.isPresent()) {
            repository.deleteTrainingFavorite(code1, code2);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", code1);
        }
    }

    public void deleteAllTrainingsFavorite(Long code1) throws RecordNotFoundException {
        Optional<training> itemT = repository.findById(code1);
        if (itemT.isPresent()) {
            repository.deleteAllTrainingsFavorite(code1);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", code1);
        }
    }
}
