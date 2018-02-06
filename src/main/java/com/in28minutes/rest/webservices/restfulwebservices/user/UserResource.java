package com.in28minutes.rest.webservices.restfulwebservices.user;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	// GET /users
    // RetrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//Get /users/{id}
	// RetrieveUser(int id)
	@GetMapping("/usersSimple/{id}")
	public User retrieveUserSimple(@PathVariable int id) {
		return service.findOne(id);
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void DeleterUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
	
	//Created
	// input -details of user
	// output- CREATED & Return the created URI
	//Response of 200 (completed)
	@PostMapping("/usersSimple")
	public void createUserSimple(@RequestBody User user) {
		User savedUser = service.save(user);
	}
	
	//Created
		// input -details of user
		// output- CREATED & Return the created URI
	 //Response of 201 (created)
		@PostMapping("/usersComplex")
		public ResponseEntity<Object> createUserComplex(@RequestBody User user) 
		{
			User savedUser = service.save(user);
			//CREATED
			// /user/{id}  savedUser.getId
			URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
			
			return ResponseEntity.created(location).build();
		}
		
		// Validation
		@PostMapping("/users")
		public ResponseEntity<Object> createUser(@Valid @RequestBody User user) 
		{
			User savedUser = service.save(user);
			//CREATED
			// /user/{id}  savedUser.getId
			URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
			
			return ResponseEntity.created(location).build();
		}
		
		
		
		
}
