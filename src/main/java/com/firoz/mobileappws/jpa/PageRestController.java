package com.firoz.mobileappws.jpa;

import java.net.URI;
import java.security.acl.Group;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.firoz.mobileappws.model.Page;
import com.firoz.mobileappws.model.User;
import com.firoz.mobileappws.repositories.PageRepository;
import com.firoz.mobileappws.repositories.UserRepository;

@RestController
public class PageRestController {

	@Autowired
	private PageRepository pageRepository;

	@GetMapping("/pages")
	public List<Page> retrieveAllGroups() {
		return (List<Page>) pageRepository.findAll();
	}
//
//	@GetMapping("/users/{id}")
//	public Optional<User> retrieveUser(@PathVariable int id) {
//		return userRepository.findById(id);
//	}
//
//	@DeleteMapping("/users/{id}")
//	public void deleteUser(@PathVariable int id) {
//		userRepository.deleteById(id);
//	}
//
//	//
//	// input - details of user
//	// output - CREATED & Return the created URI
//
//	// HATEOAS
//
//	@PostMapping("/users")
//	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//		User savedUser = userRepository.save(user);
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
//				.toUri();
//
//		return ResponseEntity.created(location).build();
//
//	}
//
//	@PostMapping("users/usersreqparam")
//	public ResponseEntity<Object> createUserCR(@RequestParam(defaultValue = "hello") String firstname,
//			@RequestParam(defaultValue = "hello") String lastname, @RequestParam(defaultValue = "hello") String email) {
//
//		User u = new User(firstname, lastname, email);
//		User savedUser = userRepository.save(u);
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
//				.toUri();
//
//		return ResponseEntity.created(location).build();
//
//	}
}
