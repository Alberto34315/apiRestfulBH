package com.alberto.apirestfulservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.repositories.exerciseRepository;
import com.alberto.apirestfulservice.repositories.trainingRepository;

@Service
public class trainingService {
	@Autowired
	trainingRepository repository;
	
	@Autowired
	exerciseRepository exerR;
	
	public List<training> getAllTrainings() {
		List<training> itemList = repository.findAll();

		if (itemList.size() > 0) {
			return itemList;
		} else {
			return new ArrayList<training>();
		}
	}
	public List<training> getAllTrainingsByIdUser(Long id) {
		List<training> itemList = repository.getAllTrainingsByIdUser(id);

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
	public training createTraining(training entity) {
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
				newEntity.setExercises(entity.getExercises());
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

	public void deleteFromExercise (Long idT, Long idE) throws RecordNotFoundException {
		Optional<training> itemT = repository.findById(idT);
		Optional<exercise> itemE = exerR.findById(idE);
		if (itemT.isPresent() && itemE.isPresent()) {
			repository.deleteFromListExercise(idT,idE);
		} else {
			throw new RecordNotFoundException("No item record exist for given id", idT);
		}
	}
}
