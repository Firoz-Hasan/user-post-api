package com.firoz.mobileappws.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firoz.mobileappws.models.User;

@Repository
public interface UserDaoRepository extends JpaRepository<User, Integer>{

}
