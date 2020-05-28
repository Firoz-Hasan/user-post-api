package com.firoz.mobileappws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
