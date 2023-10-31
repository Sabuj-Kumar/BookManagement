package com.bookManagementSystem.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookManagementSystem.dto.BookDto;
import com.bookManagementSystem.entities.Book;
import com.bookManagementSystem.entities.User;
import com.bookManagementSystem.exceptions.ResourceNotFoundExceptions;
import com.bookManagementSystem.repository.BookRepository;
import com.bookManagementSystem.repository.UserRepository;
import com.bookManagementSystem.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BookRepository bookRepo;
	@Autowired
	UserRepository userRepo;
	
	@Override
	public BookDto createBook(BookDto bookDto,Integer userId) {
        Book book = new Book();
		User user = this.userRepo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		book = this.modelMapper.map(bookDto,Book.class);
		
        book.setPublishDate(new Date());
        book.setUser(user); 
        Book createdBook = this.bookRepo.save(book);
		
		return this.modelMapper.map(createdBook,BookDto.class);
	}

	@Override
	public BookDto getBookById(Integer id) {
		Book book = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Book not found", " Id ", id));
		return this.modelMapper.map(book, BookDto.class);
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Integer id) {
		Book book = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Book not found", " Id ", id));

		book.setTitle(bookDto.getTitle());
		book.setPageCount(bookDto.getPageCount());
		book.setAuthors(bookDto.getAuthors());
		book.setDescription(bookDto.getDescription());
        book.setCategories(bookDto.getCategories());
        book.setStatus(bookDto.getStatus());
        Book updatedBook = this.bookRepo.save(book);

        BookDto updatedBookDto = this.modelMapper.map(updatedBook,BookDto.class);

		return updatedBookDto;
	}

	@Override
	public List<BookDto> getAllBook() {
		
		List<Book> bookList = this.bookRepo.findAll();
		
		List<BookDto> listOfBookDto = bookList.stream().map(book-> this.modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
		
		return listOfBookDto;
	}

	@Override
	public void deleteBookDto(Integer id) {
		Book book = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Book not found", " Id ", id));
		this.bookRepo.delete(book);
	}

	@Override
	public List<BookDto> getBookListByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		List<Book> userBookList = this.bookRepo.findByUser(user);
		
		List<BookDto> bookList = userBookList.stream().map((post)-> this.modelMapper.map(post, BookDto.class)).collect(Collectors.toList()); 
		return bookList;
	} 

}
