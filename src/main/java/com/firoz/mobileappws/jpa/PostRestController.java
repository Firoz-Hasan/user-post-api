package com.firoz.mobileappws.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.PostDto;
import com.firoz.mobileappws.exception.NotFoundException;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.daos.PostDaoRepository;

@RestController

public class PostRestController {

	
	@Autowired
	private PostService postService;

	@Autowired
	private PostDaoRepository postDaoRepository;

	@GetMapping("/posts")
	public ApiResponse retrieveAllPosts() {

		return postService.getAllPosts();
	}

	@GetMapping("/posts/{id}")
	public ApiResponse retrievePost(@PathVariable int id) {

		return postService.getPostById(id);
	}


	@RequestMapping(value = "/posts/post/{id}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getParty(@PathVariable int id) {
		Optional<Post> party = postDaoRepository.findById(id);

		if (party != null) {
			return new ResponseEntity<>(postDaoRepository.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity deletePost(@PathVariable int id) {

		return postService.deletePostById(id);
	}
	
	
	@PostMapping("/posts")
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postdto) {

		return postService.createPost(postdto);

	}
	
	
	@PostMapping("posts/postsreqparam")
	public ResponseEntity<Object> createPostByReqParam(
			@RequestParam(defaultValue = "hello") String postname,
			@RequestParam(defaultValue = "hello") String description,
			@RequestParam(defaultValue = "1") int userid
			) {

		return postService.createPostByReqParams(postname, description, userid);

	}


	@GetMapping("/posts/{id}/tags")
	public List<Tag> retrieveAlltagsForSpecificPost(@PathVariable int id) {

		Optional<Post> postOptional = postDaoRepository.findById(id);

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
