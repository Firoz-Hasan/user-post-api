package com.firoz.mobileappws.service;

import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.daos.UserDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.PageDto;
import com.firoz.mobileappws.dtos.UserDto;
import com.firoz.mobileappws.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {


    ApiResponse getAllUsers();

    ApiResponse getUserById(int id);

    ResponseEntity deleteUserById(int id);

    ResponseEntity createUser(UserDto userDto);

    ResponseEntity createUserByReqParams(String firstname, String lastname, String email);

    ApiResponse retrieveAllpostsForSpecificUser(int id);

    ResponseEntity<Object> createPostForSpecificUser( int id, Post post);

    ApiResponseWithPagination getAllUsersByPagination(String title, int page, int size, String[] sort);

}
