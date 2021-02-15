package com.alberto.apirestfulservice.controllers;

import java.util.List;

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

import javax.validation.Valid;
import com.alberto.apirestfulservice.services.exerciseService;
import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

@RestController
@RequestMapping("/exercise")
public class exerciseServiceController {

	@Autowired
	exerciseService service;

	@GetMapping
	public ResponseEntity<List<exercise>> getAllItems() {
		List<exercise> list = service.getAllExercises();

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<exercise> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		exercise entity = service.getExerciseById(id);

		return new ResponseEntity<exercise>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<List<exercise>> getAllTrainingsByIdUser(@PathVariable("id") Long id) throws RecordNotFoundException {
		List<exercise> list = service.getAllExercisesByIdUser(id);

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/search/{title}")
	public ResponseEntity<List<exercise>> getItemsByTitle(@PathVariable("title") String title) {
		List<exercise> list = service.getItemsByName(title);

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	@GetMapping("/search/{title}/user/{id}")
    public ResponseEntity<List<exercise>> getByTitleFromUser(@PathVariable("title") String title,@PathVariable("id") Long id) {
    	List<exercise> list = service.getByTitleFromUser(title,id);
 
        return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	@PostMapping
	public ResponseEntity<exercise> createItem(@Valid @RequestBody exercise myItem) {
		exercise created = service.createExercise(myItem);
		return new ResponseEntity<exercise>(created, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<exercise> UpdateItem(@Valid @RequestBody exercise myItem) throws RecordNotFoundException {
		exercise updated = service.UpdateExercise(myItem);
		return new ResponseEntity<exercise>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteExerciseById(id);
		return HttpStatus.ACCEPTED;
	}
}
