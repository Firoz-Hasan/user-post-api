package com.firoz.mobileappws.daos;

import com.firoz.mobileappws.models.ERole;
import com.firoz.mobileappws.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDaoRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
