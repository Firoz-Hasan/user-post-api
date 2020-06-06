package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.PostDto;
import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDaoRepository postDaoRepository;


    @Override
    public ApiResponse getAllPosts() {
        return new ApiResponse(200, "Success", postDaoRepository.findAll());
    }

    @Override
    public ApiResponse getPostById(int id) {
        return new ApiResponse(200, "Success", postDaoRepository.findById(id));
    }

    @Override
    public ResponseEntity deletePostById(int id) {
        postDaoRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponseDto("Post deleted successfully!"));
    }

    @Override
    public ResponseEntity createPost(PostDto postdto) {
        Post post = new Post(postdto.getPostname(), postdto.getDescription(), postdto.getUserid());
        Post savedPost = postDaoRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
                .toUri();

         ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("Post created successfully!"));
    }

    @Override
    public ResponseEntity createPostByReqParams(String postname, String desciption, int userid) {
        Post post = new Post(postname, desciption, userid);
        Post savedPost = postDaoRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
                .toUri();

         ResponseEntity.created(location).build();



        return ResponseEntity.ok(new MessageResponseDto("Post created successfully!"));
    }
}
