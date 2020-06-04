package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.TagDaoRepository;
import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.MessageResponse;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDaoRepository tagDaoRepository;


    @Override
    public ApiResponse getApiResponseTagByID(int id) {
        return new ApiResponse(200, "success", tagDaoRepository.getApiResponseTagByID(id));
    }

    @Override
    public ApiResponse getOnlyApiResponseTagNameById(int id) {
        return new ApiResponse(200, "success", tagDaoRepository.getOnlyTagNameById(id));
    }

    @Override
    public ApiResponse getOnlyApiResponseTagIdByIName(String name) {
        return new ApiResponse(200, "success", tagDaoRepository.getOnlyTagIdByName(name));
    }

    @Override
    public Optional<Tag> getOnlyFullTagByName(String name) {
        return tagDaoRepository.getOnlyFullTagByName(name);
    }

    @Override
    public ApiResponse listAllTagsGreaterThen5() {
        return new ApiResponse(200, "success", tagDaoRepository.listAllTagsGreaterThen5());

    }

    @Override
    public ApiResponse getApiResponseTagByIDwithoutQuery(int id) {
        return new ApiResponse(200, "success", tagDaoRepository.findById(id));
    }

    @Override
    public ResponseEntity deleteTagByID(int id) {
        tagDaoRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Tag deleted successfully!"));

    }
}
