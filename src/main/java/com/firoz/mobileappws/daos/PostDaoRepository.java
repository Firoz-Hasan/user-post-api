package com.firoz.mobileappws.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.models.Post;

@Repository
public interface PostDaoRepository extends JpaRepository<Post, Integer>{

}
