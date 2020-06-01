package com.firoz.mobileappws.repositories;

import com.firoz.mobileappws.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.model.Page;
import com.firoz.mobileappws.model.User;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer>{

    @Query("select e FROM Page e WHERE  e.pagemembers > ?1")
    List<Page> listAllPageMembers(int members);

}
