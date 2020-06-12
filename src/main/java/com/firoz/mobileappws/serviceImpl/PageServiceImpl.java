package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.dtos.ApiResponse;
import com.firoz.mobileappws.dtos.ApiResponseWithPagination;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.PageDto;
import com.firoz.mobileappws.models.Page;
import com.firoz.mobileappws.daos.PageDaoRepository;
import com.firoz.mobileappws.models.Tag;
import com.firoz.mobileappws.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public ApiResponseWithPagination getAllPagesByPagination(
            String pagename,
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

            List<Page> pages = new ArrayList<Page>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortingOrders));

            //in this pagePages, data will be coming fm db and save it here inside pagePages
            org.springframework.data.domain.Page<Page> pagePages;
            if (pagename == null) {
                pagePages = pageDaoRepository.findAll(pagingSort);
            }
            else
                pagePages = pageDaoRepository.findByPagenameContaining(pagename, pagingSort);
            // pagePages = tagDaoRepository.findAll(pagingSort);

            pages = pagePages.getContent();

            if (pages.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                return new ApiResponseWithPagination(HttpStatus.NO_CONTENT.value(), "No content found", null, null);
            }

            Map<String, Object> responseTagLists = new HashMap<>();
            Map<String, Object> commonResponse = new HashMap<>();
            responseTagLists.put("pages", pages);
            commonResponse.put("currentPage", pagePages.getNumber());
            commonResponse.put("totalItems", pagePages.getTotalElements());
            commonResponse.put("totalPages", pagePages.getTotalPages());

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
