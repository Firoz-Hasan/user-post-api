package com.firoz.mobileappws.daos;

import com.firoz.mobileappws.models.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.models.User;

@Repository
public interface UserDaoRepository extends JpaRepository<User, Integer>{

    Page<User> findByFirstnameContaining(String firstname, Pageable pageable);

}
