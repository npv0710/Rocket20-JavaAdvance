package com.vti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(Role.RoleName name);
}
