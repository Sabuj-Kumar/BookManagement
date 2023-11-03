package com.bookManagementSystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookManagementSystem.dto.BookDto;
import com.bookManagementSystem.dto.UserDto;
import com.bookManagementSystem.entities.Book;
import com.bookManagementSystem.entities.User;
import com.bookManagementSystem.exceptions.ResourceNotFoundExceptions;
import com.bookManagementSystem.payload.EmailRequest;
import com.bookManagementSystem.repository.BookRepository;
import com.bookManagementSystem.repository.UserRepository;
import com.bookManagementSystem.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDetailsService userDetailService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
			}
		};
	}


	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
	
		User user = this.userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		List<BookDto> bookDto = userDto.getBooks();
		List<Book> bookList = bookDto.stream().map(book -> this.modelMapper.map(book,Book.class)).collect(Collectors.toList());
		user.setBooks(bookList);
		user.setEmail(userDto.getEmail());
		user.setFullName(userDto.getFullName());
		user.setUserName(userDto.getUserName());
		 
		User updatedUser = this.userRepo.save(user);
		
		return this.modelMapper.map(updatedUser, UserDto.class); 
	}

	@Override
	public UserDto getUserById(Integer userid) {
		
		User user = this.userRepo.findById(userid).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));                          
		
		return this.modelMapper.map(user, UserDto.class); 
	}

	@Override
	public void deleteUser(Integer id) {
		
		User user = this.userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		this.userRepo.delete(user);
	}

	@Override
	public Boolean deleteBookFromUser(Integer userId, Integer bookId) {
		
        User user = this.userRepo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
	    UserDto userDto = this.modelMapper.map(user, UserDto.class);
	    Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundExceptions("Book not found", " Id ", bookId));
	    List<BookDto> userBookList = userDto.getBooks();
	    
	    if(userBookList.contains(book)) {
	    	 List<BookDto> filterBook = userBookList.stream().filter((item)-> item.getId() != bookId).toList();
	    	 List<Book> bookList = filterBook.stream().map(bookdto -> this.modelMapper.map(bookdto,Book.class)).collect(Collectors.toList());
	    	 user.setBooks(bookList); 
	    	 
	    	 User saveUser = this.userRepo.save(user);
	    	 
	    	 if(saveUser != null) {
	    		 return true;
	    	 }
	    } 
	    
	    return false;   
	}

	@Override
	public UserDto getUserByEail(EmailRequest emailRequest) {
		User user = this.userRepo.findByEmail(emailRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public void addBook(Integer userId, Integer bookId) {
		
		Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundExceptions("Book not found", " Id ", bookId));
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		List<Book> bookList = user.getBooks();
		
		bookList.add(book);
		
		user.setBooks(bookList); 
		
		this.userRepo.save(user);
	}
}
