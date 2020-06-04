package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.TagDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.MessageResponse;
import com.firoz.mobileappws.dtos.TagDto;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
        return ResponseEntity.ok(new MessageResponse("Tag deleted successfully!"));

    }

    @Override
    public ResponseEntity createTag(TagDto tagDto) {

        Tag tag = new Tag(tagDto.getTagname());
        Tag savedTag = tagDaoRepository.save(tag);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
                .toUri();

        ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponse("Tag created successfully!"));
    }

    @Override
    public ResponseEntity createTagByReqParams(String tag) {
        Tag tag1 = new Tag(tag);
        Tag savedTag = tagDaoRepository.save(tag1);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId())
                .toUri();
         ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponse("Tag created successfully!"));
    }
}
