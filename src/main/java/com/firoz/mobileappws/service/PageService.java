package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.PageDto;
import org.springframework.http.ResponseEntity;

public interface PageService {

    ApiResponse getAllPages();
    ApiResponse getPageByNumberOfMembers(int members);
    ApiResponse getPageById(int id);

    ResponseEntity deletePageById(int id);
    ResponseEntity createPage(PageDto pageDto);

    ResponseEntity createPageByReqParams(String pagename, String pgDesciption, int pgMembers);
    ApiResponseWithPagination getAllPagesByPagination(String title, int page, int size, String[] sort);

}
