package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.daos.UserDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.UserDto;
import com.firoz.mobileappws.exception.NotFoundException;
import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.models.User;
import com.firoz.mobileappws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

	@Autowired
	private UserDaoRepository userDaoRepository;
	
	@Autowired
	private PostDaoRepository postDaoRepository;


	@Autowired
	UserService userService;


	@GetMapping("/users")
	public ApiResponse retrieveAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public ApiResponse retrieveUser(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		return userService.deleteUserById(id);
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI

	// HATEOAS

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {
		return userService.createUser(userDto);

	}

	@PostMapping("users/usersreqparam")
	public ResponseEntity<Object> createUserCR(@RequestParam(defaultValue = "hello") String firstname,
			@RequestParam(defaultValue = "hello") String lastname, @RequestParam(defaultValue = "hello") String email) {

	return userService.createUserByReqParams(firstname, lastname, email);

	}

	
	
	@GetMapping("/users/{id}/posts")
	public ApiResponse retrieveAllpostsForSpecificUser(@PathVariable int id) {

		return userService.retrieveAllpostsForSpecificUser(id);
	}
	
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPostForSpecificUser(@PathVariable int id, @RequestBody Post post) {
		
		return userService.createPostForSpecificUser(id, post);

	}


	// kaj kortese nah

	@GetMapping("/users/{userId}/posts/{postID}")
	public List<Tag> retrieveAlltagsForSpecificUserAndPost(@PathVariable int userid, @PathVariable int postid) {

		Optional<User> userOptional = userDaoRepository.findById(userid);
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
