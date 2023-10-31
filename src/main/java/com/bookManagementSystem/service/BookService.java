package com.bookManagementSystem.service;

import java.util.List;

import com.bookManagementSystem.dto.BookDto;

public interface BookService {

	BookDto createBook(BookDto bookDto,Integer userId);
	BookDto getBookById(Integer id);
	BookDto updateBook(BookDto bookDto,Integer id);
	List<BookDto> getAllBook();
	void deleteBookDto(Integer id);
	List<BookDto> getBookListByUser(Integer userId);
}
