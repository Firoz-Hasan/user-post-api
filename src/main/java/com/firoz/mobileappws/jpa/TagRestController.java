package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.model.Tag;
import com.firoz.mobileappws.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class TagRestController {

	@Autowired
	private TagRepository tagRepository;

	@GetMapping("/tags")
	public List<Tag> retrieveAllGroups() {
		return (List<Tag>) tagRepository.findAll();
	}
	
	@GetMapping("/tagsbypage")
	public List<Tag> retrieveAlltags(Pageable pageable) {
		return (List<Tag>) tagRepository.listAllByPage((Pageable) pageable);
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
	@PostMapping("/tags")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Tag tag) {
		Tag savedTag = tagRepository.save(tag);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
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
