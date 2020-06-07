package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.PageDto;
import com.firoz.mobileappws.models.Page;
import com.firoz.mobileappws.daos.PageDaoRepository;
import com.firoz.mobileappws.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDaoRepository pageDaoRepository;


    @Override
    public ApiResponse getAllPages() {

        return new ApiResponse(200, "success", pageDaoRepository.findAll());
    }

    @Override
    public ApiResponse getPageByNumberOfMembers(int members) {
        return new ApiResponse(200, "success", pageDaoRepository.listAllPageMembersGreaterThenGivenMembersNumber(members));
    }

    @Override
    public ApiResponse getPageById(int id) {

        return new ApiResponse(200, "success", pageDaoRepository.findById(id));
    }

    @Override
    public ResponseEntity deletePageById(int id) {
        pageDaoRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponseDto("Page deleted successfully!"));
    }

    @Override
    public ResponseEntity createPage(PageDto pageDto) {
        Page page1 = new Page(pageDto.getPagemembers(), pageDto.getPagename(), pageDto.getPagedescription());
        Page pageTag = pageDaoRepository.save(page1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(pageTag.getId())
                .toUri();

        ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("Page created successfully!"));
    }

    @Override
    public ResponseEntity createPageByReqParams(String pagename, String pgDesciption, int pgMembers) {
        Page page1 = new Page(pgMembers, pagename, pgDesciption);
        Page pageTag = pageDaoRepository.save(page1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(pageTag.getId())
                .toUri();

        ResponseEntity.created(location).build();
        return ResponseEntity.ok(new MessageResponseDto("Page created successfully!"));
    }
}
