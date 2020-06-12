package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.daos.UserDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.UserDto;
import com.firoz.mobileappws.exception.NotFoundException;
import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.models.User;
import com.firoz.mobileappws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDaoRepository userDaoRepository;

    @Autowired
    PostDaoRepository postDaoRepository;



    @Override
    public ApiResponse getAllUsers() {
        return new ApiResponse(200, "success", userDaoRepository.findAll());
    }

    @Override
    public ApiResponse getUserById(int id) {
        return new ApiResponse(200, "success", userDaoRepository.findById(id));
    }

    @Override
    public ResponseEntity deleteUserById(int id) {
        userDaoRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponseDto("User deleted successfully!"));
    }

    @Override
    public ResponseEntity createUser(UserDto userDto) {

        User user1 = new User(userDto.getFirstname(), userDto.getLastname(), userDto.getEmail());
        User createdUser = userDaoRepository.save(user1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
                .toUri();

        ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("User created successfully!"));
    }

    @Override
    public ResponseEntity createUserByReqParams(String firstname, String lastname, String email) {
        User user1 = new User(firstname, lastname, email);
        User createdUser = userDaoRepository.save(user1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
                .toUri();

        ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("User created successfully!"));
    }

    @Override
    public ApiResponse retrieveAllpostsForSpecificUser(int id) {
        Optional<User> userOptional = userDaoRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new NotFoundException("id-" + id);
        }

        return new ApiResponse(200, "success", userOptional.get().getPosts());

    }

    @Override
    public ResponseEntity<Object> createPostForSpecificUser(int id, Post post) {

        Optional<User> userOptional = userDaoRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new NotFoundException("id-" + id);
        }

        User user = userOptional.get();

        post.setUser(user);

        postDaoRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.ok(new MessageResponseDto("User created successfully!"));
    }

    @Override
    public ApiResponseWithPagination getAllUsersByPagination(
            String username,
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

            List<User> users = new ArrayList<User>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortingOrders));

            //in this pageUsers, data will be coming fm db and save it here inside pageUsers
            Page<User> pageUsers;
            if (username == null) {
                pageUsers = userDaoRepository.findAll(pagingSort);
            }
            else
                pageUsers = userDaoRepository.findByFirstnameContaining(username, pagingSort);
            // pageUsers = tagDaoRepository.findAll(pagingSort);

            users = pageUsers.getContent();

            if (users.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                return new ApiResponseWithPagination(HttpStatus.NO_CONTENT.value(), "No content found", null, null);
            }

            Map<String, Object> responseUserLists = new HashMap<>();
            Map<String, Object> commonResponse = new HashMap<>();
            responseUserLists.put("users", users);
            commonResponse.put("currentPage", pageUsers.getNumber());
            commonResponse.put("totalItems", pageUsers.getTotalElements());
            commonResponse.put("totalPages", pageUsers.getTotalPages());

            return new ApiResponseWithPagination(HttpStatus.OK.value(), "success", commonResponse, responseUserLists);

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
