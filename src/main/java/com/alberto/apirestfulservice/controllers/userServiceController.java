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
import com.alberto.apirestfulservice.services.userService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class userServiceController {

	@Autowired
	userService service;

	@ApiOperation(value = "getAllUsers", notes = "Esta funcion nos devolvera una lista de usuarios, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping
	public ResponseEntity<List<user>> getAllUsers() {
		List<user> list = service.getAllUsers();

		return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getAllUserLessOwner", notes = "Esta funcion nos devolvera una lista de usuarios que no son amigos de un usuario, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("getAllUserLessOwner/{id}")
	public ResponseEntity<List<user>> getAllUserLessOwner(@PathVariable("id") Long id) {
		List<user> list = service.getAllUserLessOwner(id, id);

		return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "searchUserLessOwner", notes = "Esta funcion nos devolvera una lista de usuarios que no son amigos de un usuario buscanlos por su nombre, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("searchUserLessOwner/{id}/{name}")
	public ResponseEntity<List<user>> searchUserLessOwner(@PathVariable("id") Long id,@PathVariable("name") String name) {
		List<user> list = service.searchUserLessOwner(id, id, name);

		return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getAllFriends", notes = "Esta funcion nos devolvera una lista de usuarios que son amigos de un usuario, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("getAllFriends/{id}")
	public ResponseEntity<List<user>> getAllFriends(@PathVariable("id") Long id) {
		List<user> list = service.getAllFriends(id);

		return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "searchFriends", notes = "Esta funcion nos devolvera una lista de usuarios que son amigos de un usuario buscanlos por su nombre, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("searchFriends/{id}/{name}")
	public ResponseEntity<List<user>> searchFriends(@PathVariable("id") Long id, @PathVariable("name") String name) {
		List<user> list = service.searchFriends(id, name);

		return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getUserById", notes = "Esta funcion nos devolvera un usuario por su id, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<user> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		user entity = service.getUserById(id);

		return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "getUserByTitle", notes = "Esta funcion nos devolvera una lista de usuarios por el nombre del usuario pasado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/{title}")
	public ResponseEntity<List<user>> getUserByTitle(@PathVariable("title") String title) {
		List<user> list = service.getItemsByName(title);

		return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "searchCredentials", notes = "Esta funcion nos devolvera un usuario por el email y su contrase�a, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/email/{email}/{pass}")
	public ResponseEntity<user> searchCredentials(@PathVariable("email") String email,
			@PathVariable("pass") String pass) {
		user entity = service.searchCredentials(email, pass);

		return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "searchEmail", notes = "Esta funcion nos devolvera un usuario por el email, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@GetMapping("/search/email/{email}")
	public ResponseEntity<user> searchEmail(@PathVariable("email") String email) {
		user entity = service.searchEmail(email);

		return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "createUser", notes = "Esta funcion nos creara un usuario si le pasamos un objeto de tipo user, y nos devolvera el usuario creado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping
	public ResponseEntity<user> createUser(@Valid @RequestBody user myItem) {
		user created = service.createUser(myItem);
		return new ResponseEntity<user>(created, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "updateUser", notes = "Esta funcion nos actualizara un usuario si le pasamos un objeto de tipo user, y nos devolvera el usuario actualizado, mas una respuesta HTTP completa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PutMapping
	public ResponseEntity<user> updateUser(@Valid @RequestBody user myItem) throws RecordNotFoundException {
		user updated = service.UpdateUser(myItem);
		return new ResponseEntity<user>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "deleteUserById", notes = "Esta funcion nos eliminara un usuario si le pasamos su id (Long), y nos devolvera un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{id}")
	public HttpStatus deleteUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteUserById(id);
		return HttpStatus.ACCEPTED;
	}

	@ApiOperation(value = "deleteFromFriendship", notes = "Esta funcion nos eliminara la relacion entre un Usuario y otro Usuario si le pasamos el id (Long) correspondiente a cada uno, y nos devolver� un HttpStatus")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping("/{owner_id}/friend/{friend_id}")
	public HttpStatus deleteFromFriendship(@PathVariable("owner_id") Long owner_id,
			@PathVariable("friend_id") Long friend_id) throws RecordNotFoundException {
		service.deleteFromFriendship(owner_id, friend_id);
		return HttpStatus.ACCEPTED;
	}
}
