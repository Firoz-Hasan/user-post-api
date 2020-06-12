package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.PageDto;
import com.firoz.mobileappws.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PageRestController {

	@Autowired
	private PageService pageService;

	@GetMapping("/pages")
	public ApiResponse retrieveAllpages() {

		return pageService.getAllPages();
	}

	@GetMapping("/pagesbypagination")
	public ApiResponseWithPagination retrieveAllPagesByPagination(
			@RequestParam(required = false) String pagename,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id,asc") String[] sort
	) {
		return pageService.getAllPagesByPagination(
				pagename, page, size, sort
		);
	}



	@GetMapping("/pagebymembersnumber/{members}")
	public ApiResponse retrievePageByGreaterThenGivenMembersNumber(@PathVariable int members) {
		return pageService.getPageByNumberOfMembers(members);
	}


	@GetMapping("/pages/{id}")
	//Optional is a new container type that wraps a single value
	public ApiResponse retrievePage(@PathVariable int id) {
		return pageService.getPageById(id);
	}

	@DeleteMapping("/pages/{id}")
	public ResponseEntity deletePage(@PathVariable int id) {
		return pageService.deletePageById(id);
	}

	@PostMapping("/pages")
	public ResponseEntity<Object> createUser(@Valid @RequestBody PageDto pageDto) {
		return pageService.createPage(pageDto);

	}

	@PostMapping("pages/pagesreqparam")
	public ResponseEntity<Object> createUserCR(
			@RequestParam(defaultValue = "hello") String pagename,
			@RequestParam(defaultValue = "hello") String pagedescription,
			@RequestParam(defaultValue = "hello") int pagemembers) {

		return pageService.createPageByReqParams(pagename, pagedescription, pagemembers);

	}
}
