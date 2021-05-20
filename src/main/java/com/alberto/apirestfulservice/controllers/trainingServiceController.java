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
import java.util.Set;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/training")
public class trainingServiceController {

	@Autowired
	trainingService service;

	@ApiOperation(value = "getAllTraining", notes = "Esta funcion nos devolvera una lista de entrenamientos, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping
	public ResponseEntity<List<training>> getAllTraining() {
		List<training> list = service.getAllTrainings();

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getTrainingById", notes = "Esta funcion nos devolvera un entrenamiento por su id, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<training> getTrainingById(@PathVariable("id") Long id) throws RecordNotFoundException {
		training entity = service.getTrainingById(id);

		return new ResponseEntity<training>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getAllTrainingsByIdUser", notes = "Esta funcion nos devolvera una lista de entrenamientos por el id del usuario que lo creo (El num es la posicion de la fila en la que quieres que empieze a listar. Lista de 10 en 10), mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/user/{id}/Limit/{num}")
	public ResponseEntity<List<training>> getAllTrainingsByIdUser(@PathVariable("id") Long id,
			@PathVariable("num") Long num) throws RecordNotFoundException {
		List<training> list = service.getAllTrainingsByIdUser(id, num);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getAllTrainingsByIdUserIsPublished", notes = "Esta funcion nos devolvera una lista de entrenamientos por el id del usuario que lo creo y si son publicos (El num es la posicion de la fila en la que quieres que empieze a listar. Lista de 10 en 10), mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/user/published/{id}/Limit/{num}")
	public ResponseEntity<List<training>> getAllTrainingsByIdUserIsPublished(@PathVariable("id") Long id,
			@PathVariable("num") Long num) throws RecordNotFoundException {
		List<training> list = service.getAllTrainingsByIdUserIsPublished(id, num);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getTrainingsByTitle", notes = "Esta funcion nos devolvera una lista de entrenamientos por el titulo del entrenamiento, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}")
	public ResponseEntity<List<training>> getTrainingsByTitle(@PathVariable("title") String title) {
		List<training> list = service.getItemsByTitle(title);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getByTitleFromUser", notes = "Esta funcion nos devolvera una lista de entrenamientos por el titulo del getByTitleFromUser (El num es la posicion de la fila en la que quieres que empieze a listar. Lista de 10 en 10), y el id del usuario que lo creo, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}/user/{id}/Limit/{num}")
	public ResponseEntity<List<training>> getByTitleFromUser(@PathVariable("title") String title,
			@PathVariable("id") Long id, @PathVariable("num") Long num) {
		List<training> list = service.getByTitleFromUser(title, id, num);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getTrainingOfFriendsByTitle", notes = "Esta funcion nos devolvera una lista de entrenamientos publicos de los amigos de un usuario por el titulo del getByTitleFromUser, y el id del usuario que los busca (El num es la posicion de la fila en la que quieres que empieze a listar. Lista de 10 en 10), mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}/user/friend/{id}/Limit/{num}")
	public ResponseEntity<List<training>> getTrainingOfFriendsByTitle(@PathVariable("title") String title,
			@PathVariable("id") Long id, @PathVariable("num") Long num) {
		List<training> list = service.getTrainingOfFriendsByTitle(title, id, num);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "searchTrainingsFromFavorites", notes = "Esta funcion nos devolvera una lista de entrenamientos favoritos buscados por title, y el id del usuario que los busca (El num es la posicion de la fila en la que quieres que empieze a listar. Lista de 10 en 10), mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/user/{id}/favoriteTraining/{title}/Limit/{num}")
	public ResponseEntity<List<training>> searchTrainingsFromFavorites(@PathVariable("id") Long id,
			@PathVariable("title") String title, @PathVariable("num") Long num) {
		List<training> list = service.searchTrainingsFromFavorites(id, title, num);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getAllTrainingsFromFavorites", notes = "Esta funcion nos devolvera una lista de entrenamientos favoritos por el id del usuario (El num es la posicion de la fila en la que quieres que empieze a listar. Lista de 10 en 10), mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/favoriteTraining/{id}/Limit/{num}")
	public ResponseEntity<List<training>> getAllTrainingsFromFavorites(@PathVariable("id") Long id,
			@PathVariable("num") Long num) throws RecordNotFoundException {
		List<training> list = service.getAllTrainingsFromFavorites(id, num);

		return new ResponseEntity<List<training>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "isTrainingFavorite", notes = "Esta funcion nos devolvera 1 si el entrenamiento y el usuario se encuentran en la relacion y 0 si no, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Integer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/isFavorite/{idT}/user/{idU}")
	public ResponseEntity<Integer> isTrainingFavorite(@PathVariable("idT") Long code1, @PathVariable("idU") Long code2)
			throws RecordNotFoundException {
		Integer list = service.isTrainingFavorite(code1, code2);
		return new ResponseEntity<Integer>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "insertTrainingFavorite", notes = "Esta funcion insertará en la tabla favorites la relacion entre un entrenamiento y un usuario, y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = Integer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping("/insertFavorite/{idT}/user/{idU}")
	public HttpStatus insertTrainingFavorite(@PathVariable("idT") Long code1, @PathVariable("idU") Long code2)
			throws RecordNotFoundException {
		service.insertTrainingFavorite(code1, code2);
		return HttpStatus.ACCEPTED;
	}

	@ApiOperation(value = "createTraining", notes = "Esta funcion nos creara un entrenamiento si le pasamos un objeto de tipo training, y nos devolvera el entrenamiento creado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping
	public ResponseEntity<training> createTraining(@Valid @RequestBody training myItem) {
		training created = service.createTraining(myItem);
		return new ResponseEntity<training>(created, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "updateTraining", notes = "Esta funcion nos actualizara un entrenamiento si le pasamos un objeto de tipo training, y nos devolvera el entrenamiento actualizado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PutMapping
	public ResponseEntity<training> updateTraining(@Valid @RequestBody training myItem) throws RecordNotFoundException {
		training updated = service.UpdateTraining(myItem);
		return new ResponseEntity<training>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "deleteTrainingById", notes = "Esta funcion nos eliminara un entrenamiento si le pasamos su id (Long), y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{id}")
	public HttpStatus deleteTrainingById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteTrainingById(id);
		return HttpStatus.ACCEPTED;
	}

	@ApiOperation(value = "deleteFromListExercise", notes = "Esta funcion nos eliminara la relacion entre un entrenamiento y un ejercicio si le pasamos el id (Long) del entrenamiento y el id (Long) del ejercicio, y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{idT}/exercise/{idE}")
	public HttpStatus deleteFromListExercise(@PathVariable("idT") Long idT, @PathVariable("idE") Long idE)
			throws RecordNotFoundException {
		service.deleteFromExercise(idT, idE);
		return HttpStatus.ACCEPTED;
	}

	@ApiOperation(value = "deleteTrainingFavorite", notes = "Esta funcion nos eliminara la relacion entre un entrenamiento favorito y un usuario  si le pasamos el id (Long) del entrenamiento y el id (Long) del usuario, y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/delFavorite/{idT}/user/{idU}")
	public HttpStatus deleteTrainingFavorite(@PathVariable("idT") Long code1, @PathVariable("idU") Long code2)
			throws RecordNotFoundException {
		service.deleteTrainingFavorite(code1, code2);
		return HttpStatus.ACCEPTED;
	}

	@ApiOperation(value = "deleteAllTrainingsFavorite", notes = "Esta funcion nos eliminara los entrenamientos favorito si le pasamos el id (Long) del entrenamiento y el id (Long) del usuario, y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = training.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/delAllFavorite/{idT}")
	public HttpStatus deleteAllTrainingsFavorite(@PathVariable("idT") Long code1) throws RecordNotFoundException {
		service.deleteAllTrainingsFavorite(code1);
		return HttpStatus.ACCEPTED;
	}
}
