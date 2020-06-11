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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
                .toUri();
         ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("Tag created successfully!"));
    }

    @Override
    public ApiResponseOnlyMsg createTagByReqParamsAndGetApiResponse(String tag) {
        Tag tag1 = new Tag(tag);
        Tag savedTag = tagDaoRepository.save(tag1);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
                .toUri();
        ResponseEntity.created(location).build();
       // return ResponseEntity.ok(new MessageResponseDto("Tag created successfully!"));

        return new ApiResponseOnlyMsg(HttpStatus.OK.value(), "Tag created successfully!");
    }

    @Override
    public ApiResponseWithPagination getAllTagbyPage(
            String tagname,
            int page,
            int size,
            String[] sort
    ) {

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Tag> tags = new ArrayList<Tag>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Tag> pageTuts;
            if (tagname == null) {
                pageTuts = tagDaoRepository.findAll(pagingSort);
            }
            else
               pageTuts = tagDaoRepository.findByTagnameContaining(tagname, pagingSort);
               // pageTuts = tagDaoRepository.findAll(pagingSort);

            tags = pageTuts.getContent();

            if (tags.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                return new ApiResponseWithPagination(HttpStatus.NO_CONTENT.value(), "No content", null, null);
            }

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> commonresponse = new HashMap<>();
            response.put("tags", tags);
            commonresponse.put("currentPage", pageTuts.getNumber());
            commonresponse.put("totalItems", pageTuts.getTotalElements());
            commonresponse.put("totalPages", pageTuts.getTotalPages());

            return new ApiResponseWithPagination(HttpStatus.OK.value(), "success", commonresponse, response);

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
