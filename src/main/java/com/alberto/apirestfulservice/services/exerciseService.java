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

@Service
public class exerciseService {
	@Autowired
	exerciseRepository repository;

	public List<exercise> getAllExercises() {
		List<exercise> itemList = repository.findAll();

		if (itemList.size() > 0) {
			return itemList;
		} else {
			return new ArrayList<exercise>();
		}
	}
	public List<exercise> getAllExercisesByIdUser(Long id) {
		List<exercise> itemList = repository.getAllTrainingsByIdUser(id);

		if (itemList.size() > 0) {
			return itemList;
		} else {
			return new ArrayList<exercise>();
		}
	}
	public exercise getExerciseById(Long id) throws RecordNotFoundException {
		Optional<exercise> item = repository.findById(id);

		if (item.isPresent()) {
			return item.get();
		} else {
			throw new RecordNotFoundException("No item record exist for given id", id);
		}
	}
	public List<exercise> getItemsByName(String title) {
		List<exercise> itemList = repository.getByTitle(title);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<exercise>();
        }
    }
	public List<exercise> getByTitleFromUser(String title,Long code) {
        List<exercise> itemList = repository.getByTitleFromUser(title,code);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<exercise>();
        }
    }
	public exercise createExercise(exercise entity) {
		entity = repository.save(entity);
		return entity;
	}

	public exercise UpdateExercise(exercise entity) throws RecordNotFoundException {

		if (entity.getId() != null) {
			Optional<exercise> item = repository.findById(entity.getId());

			if (item.isPresent()) {
				exercise newEntity = item.get();
				// newEntity.setId(entity.getId());
				newEntity.setNameExercise(entity.getNameExercise());
				newEntity.setDescription(entity.getDescription());
				newEntity.setType(entity.getType());
				newEntity.setRepTime(entity.getRepTime());
				newEntity.setPhoto(entity.getPhoto());
				newEntity.setT(entity.getT());
				newEntity.setCreator(entity.getCreator());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				throw new RecordNotFoundException("Item not found", entity.getId());
			}
		} else {
			throw new RecordNotFoundException("No id of item given", 0l);
		}
	}

	public void deleteExerciseById(Long id) throws RecordNotFoundException {
		Optional<exercise> item = repository.findById(id);

		if (item.isPresent()) {
			repository.deleteFromExercise(id);
		} else {
			throw new RecordNotFoundException("No item record exist for given id", id);
		}
	}
}
