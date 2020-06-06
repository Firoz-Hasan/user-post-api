package com.firoz.mobileappws.daos;

import com.firoz.mobileappws.models.AuthenticateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticateDaoRepository extends JpaRepository<AuthenticateUser, Long> {
	Optional<AuthenticateUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
