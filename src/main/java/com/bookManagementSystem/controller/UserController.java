package com.bookManagementSystem.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookManagementSystem.dto.UserDto;
import com.bookManagementSystem.payload.ApiResponse;
import com.bookManagementSystem.payload.EmailRequest;
import com.bookManagementSystem.service.UserService;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id){
		
		UserDto user = this.userService.getUserById(id);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer id){
		
		UserDto updatedUserDto = this.userService.updateUser(userDto, id);
		
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Delete Succesfull",true),HttpStatus.OK);
	}
	
	@GetMapping("email")
	public ResponseEntity<UserDto> getUserByEmail(@RequestParam EmailRequest emailRequest){
		
		UserDto user  = this.userService.getUserByEail(emailRequest);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@PostMapping("delete/{userId}/book/{bookId}")
	public ResponseEntity<ApiResponse> deleteBookFromUser(@PathVariable Integer userId,@PathVariable Integer bookId){
		
		Boolean isDeleted = this.userService.deleteBookFromUser(userId, bookId);
		
		if(isDeleted) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Book Delete Succesfully",true),HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("Book not Found",false),HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("add/{userId}/book/{bookId}")
	public ResponseEntity<ApiResponse> addBook(@PathVariable Integer userId,@PathVariable Integer bookId){
		
		this.userService.addBook(userId, bookId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Book Added Succesfull",true),HttpStatus.OK );
	}
}
