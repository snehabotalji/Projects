package com.example.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Book;
import com.example.services.BookService;

@Controller
public class MainJsp {

	
	@Autowired
	private BookService bk;
	
	@GetMapping("/")
	public String viewBooks(ModelMap rs)
	{
		rs.addAttribute("books", bk.findAllBooks());
		rs.addAttribute("mode", "BOOK_VIEW");
		return "index";
	}
	
	@GetMapping("/editBook")
	public String editBook(@RequestParam long id, ModelMap rs)
	{
		Optional<Book> book1 = bk.findBook(id);
		
		rs.addAttribute("book", book1.get());
		rs.addAttribute("mode", "BOOK_EDIT");
		return "index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
	}
	
	@PostMapping("/saveBook")
	public void saveBook(@ModelAttribute Book b,HttpServletRequest rs,HttpServletResponse rr,BindingResult result) throws IOException
	{
			bk.saveBook(b);
		rs.setAttribute("books", bk.findAllBooks());
		rs.setAttribute("mode", "BOOK_VIEW");
		rr.sendRedirect("/");
	}
	
	@GetMapping("/newBook")
	public String newBooks(@ModelAttribute Book book,HttpServletRequest rs)
	{
		bk.saveBook(book);
		rs.setAttribute("mode", "BOOK_NEW");
		return "index";
	}
	
	@GetMapping("/deleteBook")
	public void deleteBook(@RequestParam long id,HttpServletRequest rs,HttpServletResponse rr) throws IOException
	{
		bk.removeBook(id);
		rr.sendRedirect("/");	
		
	}
}
