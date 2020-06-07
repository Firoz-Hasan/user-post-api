package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.daos.UserDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.UserDto;
import com.firoz.mobileappws.exception.NotFoundException;
import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.models.User;
import com.firoz.mobileappws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
}
