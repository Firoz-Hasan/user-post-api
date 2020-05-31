package com.firoz.mobileappws.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.firoz.mobileappws.exception.NotFoundException;
import com.firoz.mobileappws.model.Tag;
import com.firoz.mobileappws.model.User;
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

import com.firoz.mobileappws.model.Post;
import com.firoz.mobileappws.repositories.PostRepository;

@RestController

public class PostRestController {

	
	@Autowired
	private PostRepository postRepository;
	

	@GetMapping("/posts")
	public List<Post> retrieveAllPosts() {
		return (List<Post>) postRepository.findAll();
	}

	@GetMapping("/posts/{id}")
	public Optional<Post> retrievePost(@PathVariable int id) {
		return postRepository.findById(id);
	}

	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable int id) {
		postRepository.deleteById(id);
	}
	
	
	@PostMapping("/posts")
	public ResponseEntity<Object> createPost(@Valid @RequestBody Post post) {
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	@PostMapping("posts/postsreqparam")
	public ResponseEntity<Object> createPostByReqParam(
			@RequestParam(defaultValue = "hello") String postname,
			@RequestParam(defaultValue = "hello") String description,
			@RequestParam(defaultValue = "1") int userid
			) {

		Post post = new Post(postname, description, userid);
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}


	@GetMapping("/posts/{id}/tags")
	public List<Tag> retrieveAlltagsForSpecificPost(@PathVariable int id) {

		Optional<Post> postOptional = postRepository.findById(id);

		if(!postOptional.isPresent()) {
			throw new NotFoundException("id-" + id);
		}

		return postOptional.get().getTags();
	}



//	@GetMapping("/posts/gettest/{id}")
//	public Post retrieveUser1(@PathVariable int id) {
//		return new Post(postRepository.get());
//	}
	


}
