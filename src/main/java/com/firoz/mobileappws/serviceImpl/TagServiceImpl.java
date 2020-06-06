package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.TagDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponseDto;
import com.firoz.mobileappws.dtos.MessageResponseDto;

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
    public ApiResponseDto getApiResponseTagByID(int id) {
        return new ApiResponseDto(200, "success", tagDaoRepository.getApiResponseTagByID(id));
    }

    @Override
    public ApiResponseDto getOnlyApiResponseTagNameById(int id) {
        return new ApiResponseDto(200, "success", tagDaoRepository.getOnlyTagNameById(id));
    }

    @Override
    public ApiResponseDto getOnlyApiResponseTagIdByIName(String name) {
        return new ApiResponseDto(200, "success", tagDaoRepository.getOnlyTagIdByName(name));
    }

    @Override
    public Optional<Tag> getOnlyFullTagByName(String name) {
        return tagDaoRepository.getOnlyFullTagByName(name);
    }

    @Override
    public ApiResponseDto listAllTagsGreaterThen5() {
        return new ApiResponseDto(200, "success", tagDaoRepository.listAllTagsGreaterThen5());

    }

    @Override
    public ApiResponseDto listAllTags() {
        return new ApiResponseDto(200, "success", tagDaoRepository.findAll());

    }

    @Override
    public ApiResponseDto getApiResponseTagByIDwithoutQuery(int id) {
        return new ApiResponseDto(200, "success", tagDaoRepository.findById(id));
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
}
