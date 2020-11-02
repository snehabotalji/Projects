package com.example.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookRepository;
import com.example.model.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bk;
	
	public Collection<Book> findAllBooks()
	{
		List<Book> l = new ArrayList<Book>();
		
		for(Book book : bk.findAll())
		{
			l.add(book);
		}
		
		return l;
	}
	
	public Optional<Book> findBook(long id)
	{
		return bk.findById(id);
	}
	
	public void removeBook(long id)
	{
		bk.deleteById(id);
	}
	
	public void saveBook(Book book)
	{
		bk.save(book);
	}
}
