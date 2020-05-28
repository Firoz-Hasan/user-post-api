package com.firoz.mobileappws.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.firoz.mobileappws.model.Tag;
import com.firoz.mobileappws.model.User;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>{

	@Query(value = "select e FROM Tag e WHERE e.id = ?1")
    Page<Tag> listAllByPage(org.springframework.data.domain.Pageable pageable);
}
