package com.chnbin.springbootdemo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chnbin.springbootdemo.domain.Book;
import com.chnbin.springbootdemo.service.BookService;

@RestController
@RequestMapping("/api/v1/")
public class BookApp {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getAll() {
		return bookService.findAll();
	}
}
