package com.chnbin.springbootdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnbin.springbootdemo.domain.Book;
import com.chnbin.springbootdemo.domain.BookRepository;

// 服務層，註記這個Controller才可以注入
@Service
public class BookService {
	@Autowired
	private  BookRepository bookRepository;
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
}
