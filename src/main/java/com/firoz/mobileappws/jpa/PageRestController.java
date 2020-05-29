package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.model.Page;
import com.firoz.mobileappws.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PageRestController {

	@Autowired
	private PageRepository pageRepository;

	@GetMapping("/pages")
	public List<Page> retrieveAllpages() {
		return (List<Page>) pageRepository.findAll();
	}



	@GetMapping("/pages/{id}")
	//Optional is a new container type that wraps a single value
	public Optional<Page> retrievePage(@PathVariable int id) {
		return pageRepository.findById(id);
	}

	@DeleteMapping("/pages/{id}")
	public void deletePage(@PathVariable int id) {
		pageRepository.deleteById(id);
	}

	@PostMapping("/pages")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Page page) {
		Page savedUser = pageRepository.save(page);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PostMapping("pages/pagesreqparam")
	public ResponseEntity<Object> createUserCR(
			@RequestParam(defaultValue = "hello") String pagename,
			@RequestParam(defaultValue = "hello") String pagedescription,
			@RequestParam(defaultValue = "hello") int pagemembers) {

		Page page = new Page(pagemembers, pagedescription, pagename);
		Page savedPage = pageRepository.save(page);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPage.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
}
