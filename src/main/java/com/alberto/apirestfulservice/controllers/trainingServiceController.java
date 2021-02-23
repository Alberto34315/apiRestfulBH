package com.alberto.apirestfulservice.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.exercise;
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;
import com.alberto.apirestfulservice.services.exerciseService;
import com.alberto.apirestfulservice.services.trainingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/training")
public class trainingServiceController {

	@Autowired
	trainingService service;

	@ApiOperation(value = "getAllTraining", notes = "Esta función nos devolverá una lista de entrenamientos, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping
	public ResponseEntity<List<training>> getAllTraining() {
		List<training> list = service.getAllTrainings();

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getTrainingById", notes = "Esta función nos devolverá un entrenamiento por su id, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<training> getTrainingById(@PathVariable("id") Long id) throws RecordNotFoundException {
		training entity = service.getTrainingById(id);

		return new ResponseEntity<training>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "getAllTrainingsByIdUser", notes = "Esta función nos devolverá una lista de entrenamientos por el id del usuario que lo creo, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/user/{id}")
	public ResponseEntity<List<training>> getAllTrainingsByIdUser(@PathVariable("id") Long id) throws RecordNotFoundException {
		List<training> list = service.getAllTrainingsByIdUser(id);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "getTrainingsByTitle", notes = "Esta función nos devolverá una lista de entrenamientos por el titulo del entrenamiento, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}")
    public ResponseEntity<List<training>> getTrainingsByTitle(@PathVariable("title") String title) {
    	List<training> list = service.getItemsByTitle(title);
 
        return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@ApiOperation(value = "getByTitleFromUser", notes = "Esta función nos devolverá una lista de entrenamientos por el titulo del getByTitleFromUser, y el id del usuario que lo creo, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}/user/{id}")
    public ResponseEntity<List<training>> getByTitleFromUser(@PathVariable("title") String title,@PathVariable("id") Long id) {
    	List<training> list = service.getByTitleFromUser(title,id);
 
        return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@ApiOperation(value = "createTraining", notes = "Esta función nos creará un entrenamiento si le pasamos un objeto de tipo training, y nos devolverá el entrenamiento creado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping
	public ResponseEntity<training> createTraining(@Valid @RequestBody training myItem) {
		//System.out.println(myItem);
		training created = service.createTraining(myItem);
		return new ResponseEntity<training>(created, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "updateTraining", notes = "Esta función nos actualizara un entrenamiento si le pasamos un objeto de tipo training, y nos devolverá el entrenamiento actualizado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PutMapping
	public ResponseEntity<training> updateTraining(@Valid @RequestBody training myItem) throws RecordNotFoundException {
		training updated = service.UpdateTraining(myItem);
		return new ResponseEntity<training>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "deleteTrainingById", notes = "Esta función nos eliminará un entrenamiento si le pasamos su id (Long), y nos devolverá un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{id}")
	public HttpStatus deleteTrainingById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteTrainingById(id);
		return HttpStatus.ACCEPTED;
	}
	
	@ApiOperation(value = "deleteFromListExercise", notes = "Esta función nos eliminará la relación entre un entrenamiento y un ejercicio si le pasamos el id (Long) del entrenamiento y el id (Long) del ejercicio, y nos devolverá un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{idT}/exercise/{idE}")
	public HttpStatus deleteFromListExercise(@PathVariable("idT") Long idT, @PathVariable("idE") Long idE) throws RecordNotFoundException {
		service.deleteFromExercise(idT, idE);
		return HttpStatus.ACCEPTED;
	}
}
