package com.alberto.apirestfulservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.services.exerciseService;
import com.alberto.apirestfulservice.services.trainingService;

@RestController
@RequestMapping("/training")
public class trainingServiceController {

	@Autowired
	trainingService service;

	@GetMapping
	public ResponseEntity<List<training>> getAllItems() {
		List<training> list = service.getAllTrainings();

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<training> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		training entity = service.getTrainingById(id);

		return new ResponseEntity<training>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	@GetMapping("/search/{title}")
    public ResponseEntity<List<training>> getItemsByTitle(@PathVariable("title") String title) {
    	List<training> list = service.getItemsByTitle(title);
 
        return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	@PostMapping
	public ResponseEntity<training> createItem(@Valid @RequestBody training myItem) {
		training created = service.createTraining(myItem);
		return new ResponseEntity<training>(created, new HttpHeaders(), HttpStatus.OK);
	}


	@PutMapping
	public ResponseEntity<training> UpdateItem(@Valid @RequestBody training myItem) throws RecordNotFoundException {
		training updated = service.UpdateTraining(myItem);
		return new ResponseEntity<training>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteTrainingById(id);
		return HttpStatus.ACCEPTED;
	}
}
