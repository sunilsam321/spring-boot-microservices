package com.withcode.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.withcode.demo.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	 Optional<User> findByEmail(String email);

}
