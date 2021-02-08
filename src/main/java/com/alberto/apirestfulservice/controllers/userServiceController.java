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
import com.alberto.apirestfulservice.model.training;
import com.alberto.apirestfulservice.model.user;
import com.alberto.apirestfulservice.services.userService;

@RestController
@RequestMapping("/user")
public class userServiceController {

	 @Autowired
	 userService service;
	
	 @GetMapping
	    public ResponseEntity<List<user>> getAllItems() {
	        List<user> list = service.getAllUsers();
	 
	        return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<user> getItemById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	user entity = service.getUserById(id);
	 
	        return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
	    }

		@GetMapping("/search/{title}")
		public ResponseEntity<List<user>> getItemsByTitle(@PathVariable("title") String title) {
			List<user> list = service.getItemsByName(title);

			return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
		}
		@GetMapping("/search/email/{email}/{pass}")
		public ResponseEntity<user> searchCredentials(@PathVariable("email")String email, @PathVariable("pass")String pass) {
			user entity = service.searchCredentials(email, pass);

			return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
		}
	   @PostMapping
	    public ResponseEntity<user> createItem(@Valid @RequestBody user myItem){
	    	user created = service.createUser(myItem);
	        return new ResponseEntity<user>(created, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PutMapping
	    public ResponseEntity<user> UpdateItem(@Valid @RequestBody user myItem)
	                                                    throws RecordNotFoundException {
	    	user updated = service.UpdateUser(myItem);
	        return new ResponseEntity<user>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @DeleteMapping("/{id}")
	    public HttpStatus deleteItemById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	        service.deleteUserById(id);
	        return HttpStatus.ACCEPTED;
	    }
}
