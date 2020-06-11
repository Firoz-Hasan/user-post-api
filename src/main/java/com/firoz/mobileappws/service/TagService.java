package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseOnlyMsg;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.TagDto;
import com.firoz.mobileappws.models.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface TagService {

    ApiResponse getApiResponseTagByID(int id);

    ApiResponse getOnlyApiResponseTagNameById(int id);

    ApiResponse getOnlyApiResponseTagIdByIName(String name);

    Optional<Tag> getOnlyFullTagByName(String name);

    ApiResponse listAllTagsGreaterThen5();

    ApiResponse listAllTags();

    ApiResponse getApiResponseTagByIDwithoutQuery(int id);

    ResponseEntity deleteTagByID(int id);

    ResponseEntity createTag(TagDto tagDto);

    ResponseEntity createTagByReqParams(String tag);

    ApiResponseOnlyMsg createTagByReqParamsAndGetApiResponse(String tag);

    ApiResponseWithPagination getAllTagbyPage(String title, int page, int size, String[] sort);
}
