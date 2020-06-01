package com.firoz.mobileappws.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.firoz.mobileappws.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>{


    // kaj kortese nah
/*	@Query(value = "select e FROM Tag e WHERE e.id = ?1")
    List<Tag> listAllByPage(int id  );*/



    @Query("select e.tagname FROM Tag e WHERE e.id = ?1")
    Optional<Tag> tagID(@Param("id")int id);

    @Query("select e.id FROM Tag e WHERE e.tagname = ?1")
    Optional<Tag> tagName(@Param("tagname")String name);


}
