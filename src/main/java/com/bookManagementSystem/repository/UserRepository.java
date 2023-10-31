package com.bookManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookManagementSystem.entities.User;
import com.bookManagementSystem.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);
	
	User findByRole(Role role);
}
