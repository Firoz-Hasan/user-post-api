package com.firoz.mobileappws.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.firoz.mobileappws.models.Tag;
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

import com.firoz.mobileappws.exception.NotFoundException;
import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.models.User;
import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.repositories.UserRepository;

@RestController
public class UserRestController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostDaoRepository postDaoRepository;



	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		return userRepository.findById(id);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI

	// HATEOAS

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PostMapping("users/usersreqparam")
	public ResponseEntity<Object> createUserCR(@RequestParam(defaultValue = "hello") String firstname,
			@RequestParam(defaultValue = "hello") String lastname, @RequestParam(defaultValue = "hello") String email) {

		User u = new User(firstname, lastname, email);
		User savedUser = userRepository.save(u);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllpostsForSpecificUser(@PathVariable int id) {

		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new NotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts();
	}
	
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPostForSpecificUser(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new NotFoundException("id-" + id);
		}

		User user = userOptional.get();
		
		post.setUser(user);
		
		postDaoRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}


	// kaj kortese nah

	@GetMapping("/users/{userId}/posts/{postID}")
	public List<Tag> retrieveAlltagsForSpecificUserAndPost(@PathVariable int userid, @PathVariable int postid) {

		Optional<User> userOptional = userRepository.findById(userid);
		Optional<Post> postOptional = postDaoRepository.findById(postid);

		if(!userOptional.isPresent()) {
			throw new NotFoundException("id-" + userid);
		}
		if(!postOptional.isPresent()) {
			throw new NotFoundException("id-" + postid);
		}


		return postOptional.get().getTags();
	}


}
