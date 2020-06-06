package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.PostDto;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ApiResponse getAllPosts();
    ApiResponse getPostById(int id);
    ResponseEntity deletePostById(int id);
    ResponseEntity createPost(PostDto postdto);
    ResponseEntity createPostByReqParams(String postname, String desciption, int userid);
}
