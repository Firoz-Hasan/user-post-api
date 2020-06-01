package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.model.Tag;
import com.firoz.mobileappws.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RepositoryRestResource(exported = false)
public class TagRestController {

	@Autowired
	private TagRepository tagRepository;

	@GetMapping("/tags")
	public List<Tag> retrieveAlltags() {
		return (List<Tag>) tagRepository.findAll();
	}
	
	/*@GetMapping("/tagsbypage")
	public List<Tag> retrieveAlltagsQuery(Pageable pageable) {
		return (List<Tag>) tagRepository.listAllByPage((Pageable) pageable);
	}*/

	@GetMapping("/tagnamebyid/{id}")
	public Optional<Tag> retrieveTagName(@PathVariable int id) {

		return tagRepository.tagID(id);
	}

	@GetMapping("/tagidbyname/{name}")
	public Optional<Tag> retrieveTagID(@PathVariable String name) {

		return tagRepository.tagName(name);
	}

	@GetMapping("/tags/{id}")
	public Optional<Tag> retrieveTag(@PathVariable int id) {
		return tagRepository.findById(id);
	}


	@DeleteMapping("/tags/{id}")
	public void deleteTag(@PathVariable int id) {
		tagRepository.deleteById(id);
	}

	@PostMapping("/tags")
	public ResponseEntity<Object> createTags(@Valid @RequestBody Tag tag) {
		Tag savedTag = tagRepository.save(tag);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PostMapping("/tagsbyreqparam")
	public ResponseEntity<Object> createTagsByParam(
			@RequestParam String tagname

	){
		Tag tag = new Tag(tagname);
		Tag savedTag = tagRepository.save(tag);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
