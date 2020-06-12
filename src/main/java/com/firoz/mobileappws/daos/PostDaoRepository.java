package com.firoz.mobileappws.daos;

import com.firoz.mobileappws.models.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.models.Post;

@Repository
public interface PostDaoRepository extends JpaRepository<Post, Integer>{
    Page<Post> findByPostnameContaining(String postname, Pageable pageable);
}
