package com.firoz.mobileappws.daos;

import com.firoz.mobileappws.models.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.models.Page;

import java.util.List;

@Repository
public interface PageDaoRepository extends JpaRepository<Page, Integer>{

    @Query("select e FROM Page e WHERE  e.pagemembers > ?1")
    List<Page> listAllPageMembersGreaterThenGivenMembersNumber(int members);

    org.springframework.data.domain.Page<Page> findByPagenameContaining(String pagename, Pageable pageable);
}
