package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.PostDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.PostDto;
import com.firoz.mobileappws.models.Post;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.service.PostService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ApiResponseWithPagination getAllPostsByPagination(
            String postname,
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

            List<Post> posts = new ArrayList<Post>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortingOrders));

            //in this pagePosts, data will be coming fm db and save it here inside pagePosts
            Page<Post> pagePosts;
            if (postname == null) {
                pagePosts = postDaoRepository.findAll(pagingSort);
            }
            else
                pagePosts = postDaoRepository.findByPostnameContaining(postname, pagingSort);
            // pagePosts = tagDaoRepository.findAll(pagingSort);

            posts = pagePosts.getContent();

            if (posts.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                return new ApiResponseWithPagination(HttpStatus.NO_CONTENT.value(), "No content found", null, null);
            }

            Map<String, Object> responseTagLists = new HashMap<>();
            Map<String, Object> commonResponse = new HashMap<>();
            responseTagLists.put("posts", posts);
            commonResponse.put("currentPage", pagePosts.getNumber());
            commonResponse.put("totalItems", pagePosts.getTotalElements());
            commonResponse.put("totalPages", pagePosts.getTotalPages());

            return new ApiResponseWithPagination(HttpStatus.OK.value(), "success", commonResponse, responseTagLists);

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
