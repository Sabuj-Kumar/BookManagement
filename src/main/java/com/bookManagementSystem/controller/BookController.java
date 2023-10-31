package com.bookManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookManagementSystem.dto.BookDto;
import com.bookManagementSystem.payload.ApiResponse;
import com.bookManagementSystem.service.BookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto,@PathVariable Integer userId){
		
		BookDto createdBook =  this.bookService.createBook(bookDto,userId);
		
		return new ResponseEntity<BookDto>(createdBook,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{bookId}")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer bookId){
		
		BookDto updateBook = this.bookService.updateBook(bookDto, bookId);
		
		return new ResponseEntity<BookDto>(updateBook,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable Integer id){
		BookDto updateBook = this.bookService.getBookById(id);
		return new ResponseEntity<BookDto>(updateBook,HttpStatus.OK);

	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteBook(@PathVariable Integer id){
		
		this.bookService.deleteBookDto(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Book Delete succcesfull",true),HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<BookDto>> getUserAllBook(@PathVariable Integer id){
		List<BookDto> booklist = this.bookService.getBookListByUser(id);
		
		return new ResponseEntity<List<BookDto>>(booklist,HttpStatus.OK);
	}
	
	@GetMapping("/All")
	public ResponseEntity<List<BookDto>> getAllBooks(){
		
		List<BookDto> booklist = this.bookService.getAllBook();
		
		return new ResponseEntity<List<BookDto>>(booklist,HttpStatus.OK);
	}
}
