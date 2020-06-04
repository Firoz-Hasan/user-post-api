package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.daos.TagDaoRepository;
import com.firoz.mobileappws.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
	private TagDaoRepository tagDaoRepository;

	@Autowired
	private TagService tagService;

	@GetMapping("/tags")
	public List<Tag> retrieveAlltags() {
		return (List<Tag>) tagDaoRepository.findAll();
	}
	
	/*@GetMapping("/tagsbypage")
	public List<Tag> retrieveAlltagsQuery(Pageable pageable) {
		return (List<Tag>) tagRepository.listAllByPage((Pageable) pageable);
	}*/

	@GetMapping("/tagbyid/{id}")
	public ApiResponse retrieveTagName2(@PathVariable int id) {

		return tagService.getApiResponseTagByID(id);
	}

	@GetMapping("/tagnamebyid/{id}")
	public ApiResponse retrieveTagName(@PathVariable int id) {

		return tagService.getOnlyApiResponseTagNameById(id);
	}

	@GetMapping("/tagidbyname/{name}")
	public ApiResponse retrieveTagID(@PathVariable String name) {

		return tagService.getOnlyApiResponseTagIdByIName(name);
	}

	@GetMapping("/fulltagbyname/{name}")
	public Optional<Tag> retrieveFullTag(@PathVariable String name) {

		return tagService.getOnlyFullTagByName(name);
	}

	@GetMapping("/alltagsgreaterthen5")
	public ApiResponse alltags() {

		return tagService.listAllTagsGreaterThen5();
	}


	@GetMapping("/tags/{id}")
	public ApiResponse retrieveTag(@PathVariable int id) {

		return tagService.getApiResponseTagByIDwithoutQuery(id);
	}


	@DeleteMapping("/tags/{id}")
	public ResponseEntity deleteTag(@PathVariable int id) {

		return tagService.deleteTagByID(id);
	}

	@PostMapping("/tags")
	public ResponseEntity<Object> createTags(@Valid @RequestBody Tag tag) {
		Tag savedTag = tagDaoRepository.save(tag);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PostMapping("/tagsbyreqparam")
	public ResponseEntity<Object> createTagsByParam(
			@RequestParam String tagname

	){
		Tag tag = new Tag(tagname);
		Tag savedTag = tagDaoRepository.save(tag);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
