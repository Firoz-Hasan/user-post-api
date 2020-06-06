package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.ApiResponseDto;
import com.firoz.mobileappws.dtos.TagDto;
import com.firoz.mobileappws.models.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TagService {

    ApiResponseDto getApiResponseTagByID(int id);

    ApiResponseDto getOnlyApiResponseTagNameById(int id);

    ApiResponseDto getOnlyApiResponseTagIdByIName(String name);

    Optional<Tag> getOnlyFullTagByName(String name);

    ApiResponseDto listAllTagsGreaterThen5();

    ApiResponseDto listAllTags();

    ApiResponseDto getApiResponseTagByIDwithoutQuery(int id);

    ResponseEntity deleteTagByID(int id);

    ResponseEntity createTag(TagDto tagDto);

    ResponseEntity createTagByReqParams(String tag);

}
