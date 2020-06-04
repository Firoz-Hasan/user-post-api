package com.firoz.mobileappws.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.firoz.mobileappws.models.Tag;

@Repository
public interface TagDaoRepository extends JpaRepository<Tag, Integer>{


    // kaj kortese nah
/*	@Query(value = "select e FROM Tag e WHERE e.id = ?1")
    List<Tag> listAllByPage(int id  );*/


    @Query("select e.tagname FROM Tag e WHERE e.id = ?1")
    Optional<Tag> getOnlyTagNameById(@Param("id")int id);

    @Query("select e.id FROM Tag e WHERE e.tagname = ?1")
    Optional<Tag> getOnlyTagIdByName(@Param("tagname")String name);

    @Query("select e FROM Tag e WHERE  e.id > 5")
    List<Tag> listAllTagsGreaterThen5();

    @Query("select e FROM Tag e WHERE e.tagname = ?1")
    Optional<Tag> getOnlyFullTagByName(@Param("tagname")String name);

    @Query("select e FROM Tag e WHERE e.id = ?1")
    Optional<Tag> getApiResponseTagByID(@Param("id")int id);

}
