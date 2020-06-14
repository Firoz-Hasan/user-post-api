package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.TagDaoRepository;
import com.firoz.mobileappws.dtos.*;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDaoRepository tagDaoRepository;


    @Override
    public ApiResponse getApiResponseTagByID(int id) {
        return new ApiResponse(200, "success", tagDaoRepository.getApiResponseTagByID(id));
    }

    @Override
    public ApiResponse getOnlyApiResponseTagNameById(int id) {
        return new ApiResponse(200, "success", tagDaoRepository.getOnlyTagNameById(id));
    }

    @Override
    public ApiResponse getOnlyApiResponseTagIdByIName(String name) {
        return new ApiResponse(200, "success", tagDaoRepository.getOnlyTagIdByName(name));
    }

    @Override
    public Optional<Tag> getOnlyFullTagByName(String name) {
        return tagDaoRepository.getOnlyFullTagByName(name);
    }

    @Override
    public ApiResponse listAllTagsGreaterThen5() {
        return new ApiResponse(200, "success", tagDaoRepository.listAllTagsGreaterThen5());

    }

    @Override
    public ApiResponse listAllTags() {
        return new ApiResponse(200, "success", tagDaoRepository.findAll());

    }

    @Override
    public ApiResponse getApiResponseTagByIDwithoutQuery(int id) {
        return new ApiResponse(200, "success", tagDaoRepository.findById(id));
    }

    @Override
    public ResponseEntity deleteTagByID(int id) {
        tagDaoRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponseDto("Tag deleted successfully!"));

    }

    @Override
    public ResponseEntity createTag(TagDto tagDto) {

        Tag tag1 = new Tag(tagDto.getTgname());
        Tag savedTag = tagDaoRepository.save(tag1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
                .toUri();

        ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("Tag created successfully!"));
    }

    @Override
    public ResponseEntity createTagByReqParams(String tag) {
        Tag tag1 = new Tag(tag);
        Tag savedTag = tagDaoRepository.save(tag1);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()// this woould return current req URI - localhost:8083/tagsbyreqparam
                .path("/{id}") // then appending /id to it
                .buildAndExpand(savedTag.getId()) // then replacing savedTag.getId()
                .toUri();
         ResponseEntity.created(location).build();


         // HATEOAS - hypermedia as the engine of the applicaiton state
       EntityModel<Tag> model = new EntityModel<>(savedTag);

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listAllTags());

        model.add(linkTo.withRel("all-tags"));


        return ResponseEntity.ok(new MessageResponseDto("Tag created successfully!"));


    }

    @Override
    public ApiResponseMsgWthStatus createTagByReqParamsAndGetApiResponse(String tag) {
        Tag tag1 = new Tag(tag);
        Tag savedTag = tagDaoRepository.save(tag1);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
                .toUri();
        ResponseEntity.created(location).build();
       // return ResponseEntity.ok(new MessageResponseDto("Tag created successfully!"));

        return new ApiResponseMsgWthStatus(HttpStatus.OK.value(), "Tag created successfully!");
    }

    @Override
    public ApiResponseWithPagination getAllTagsByPagination(
            String tagname,
            int page,
            int size,
            String[] sort
    ) {

        try {
            List<Sort.Order> sortingOrders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // sort/order by multiple columns
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    sortingOrders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                sortingOrders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Tag> tags = new ArrayList<Tag>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortingOrders));

            //in this pageTags, data will be coming fm db and save it here inside pageTags
            Page<Tag> pageTags;
            if (tagname == null) {
                pageTags = tagDaoRepository.findAll(pagingSort);
            }
            else
               pageTags = tagDaoRepository.findByTagnameContaining(tagname, pagingSort);
               // pageTags = tagDaoRepository.findAll(pagingSort);

            tags = pageTags.getContent();

            if (tags.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                return new ApiResponseWithPagination(HttpStatus.NO_CONTENT.value(), "No content found", null, null);
            }

            Map<String, Object> responseTagLists = new HashMap<>();
            Map<String, Object> commonResponse = new HashMap<>();
            responseTagLists.put("tags", tags);
            commonResponse.put("currentPage", pageTags.getNumber());
            commonResponse.put("totalItems", pageTags.getTotalElements());
            commonResponse.put("totalPages", pageTags.getTotalPages());

            return new ApiResponseWithPagination(HttpStatus.OK.value(), "success", commonResponse, responseTagLists);

        } catch (Exception e) {

            return new ApiResponseWithPagination(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failure", null, null);

            // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
