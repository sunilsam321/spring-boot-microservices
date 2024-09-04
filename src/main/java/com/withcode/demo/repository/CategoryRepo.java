package com.withcode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.withcode.demo.entities.Category;

public interface CategoryRepo extends JpaRepository <Category, Integer>{

}
