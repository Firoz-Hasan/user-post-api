package com.firoz.mobileappws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.model.Page;
import com.firoz.mobileappws.model.User;

@Repository
//@Repository - This is to indicate that the class defines a data repository.
public interface PageRepository extends JpaRepository<Page, Integer>{

}
