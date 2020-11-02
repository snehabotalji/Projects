package com.example.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Book;
import com.example.services.BookService;

@RestController
public class MainController {

	@Autowired
	private BookService bs;
	
	@GetMapping(value="/hello")
	public String hello()
	{
		return "hello world";
	}
	
	@GetMapping(value="/findAllBooks")
	public Collection<Book> getAllBooks()
	{
		return bs.findAllBooks();
	}
	
	@GetMapping("/removeBook")
	public void removeBook(@RequestParam long id)
	{
	bs.removeBook(id);	
	}
}
