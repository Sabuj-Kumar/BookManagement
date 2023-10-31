package com.bookManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookManagementSystem.entities.Book;
import com.bookManagementSystem.entities.User;

public interface BookRepository extends JpaRepository<Book,Integer>{

	List<Book> findByUser(User user);
}
