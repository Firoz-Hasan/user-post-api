package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.model.Page;
import com.firoz.mobileappws.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
