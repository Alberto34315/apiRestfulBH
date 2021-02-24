package com.alberto.apirestfulservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/exercise")
public class exerciseServiceController {

	@Autowired
	exerciseService service;

	@ApiOperation(value = "getAllExercises", notes = "Esta funcion nos devolvera una lista de ejercicios, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping
	public ResponseEntity<List<exercise>> getAllExercises() {
		List<exercise> list = service.getAllExercises();

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getExerciseById", notes = "Esta funcion nos devolvera un ejercicio por su id, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<exercise> getExerciseById(@PathVariable("id") Long id) throws RecordNotFoundException {
		exercise entity = service.getExerciseById(id);

		return new ResponseEntity<exercise>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getAllExercisesByIdUser", notes = "Esta funcion nos devolvera una lista de ejercicios por el id del usuario que lo creo, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/user/{id}")
	public ResponseEntity<List<exercise>> getAllExercisesByIdUser(@PathVariable("id") Long id)
			throws RecordNotFoundException {
		List<exercise> list = service.getAllExercisesByIdUser(id);

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getExerciseByTitle", notes = "Esta funcion nos devolvera una lista de ejercicios por el nombre del ejercicio pasado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}")
	public ResponseEntity<List<exercise>> getExerciseByTitle(@PathVariable("title") String title) {
		List<exercise> list = service.getItemsByName(title);

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getExerciseByTitleFromUser", notes = "Esta función nos devolvera una lista de ejercicios por el nombre del ejercicio pasado, y el id del usuario que lo creo, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}/user/{id}")
	public ResponseEntity<List<exercise>> getExerciseByTitleFromUser(@PathVariable("title") String title,
			@PathVariable("id") Long id) {
		List<exercise> list = service.getByTitleFromUser(title, id);

		return new ResponseEntity<List<exercise>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "createExercise", notes = "Esta funcion nos creara un ejercicio si le pasamos un objeto de tipo exercise, y nos devolvera el ejercicio creado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping
	public ResponseEntity<exercise> createExercise(@Valid @RequestBody exercise myItem) {
		exercise created = service.createExercise(myItem);
		return new ResponseEntity<exercise>(created, new HttpHeaders(), HttpStatus.OK);
	}


	@ApiOperation(value = "updateExercise", notes = "Esta funcion nos actualizara un ejercicio si le pasamos un objeto de tipo exercise, y nos devolvera el ejercicio actualizado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PutMapping
	public ResponseEntity<exercise> updateExercise(@Valid @RequestBody exercise myItem) throws RecordNotFoundException {
		exercise updated = service.UpdateExercise(myItem);
		return new ResponseEntity<exercise>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "deleteExerciseById", notes = "Esta funcion nos eliminara un ejercicio si le pasamos su id (Long), y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = exercise.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{id}")
	public HttpStatus deleteExerciseById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteExerciseById(id);
		return HttpStatus.ACCEPTED;
	}
}
