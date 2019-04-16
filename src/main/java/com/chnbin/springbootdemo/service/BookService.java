package com.chnbin.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnbin.springbootdemo.domain.Book;
import com.chnbin.springbootdemo.domain.BookRepository;

// 服務層，註記這個Controller才可以注入
@Service
public class BookService {
	@Autowired
	private  BookRepository bookRepository;
	
	/**
	 * Query all books
	 * @return
	 */
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	/*
	 * Insert a book
	 */
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public Optional<Book> findBookById(long id) {
		return bookRepository.findById(id);
	}
	
	public void deleteById(long id) {
		bookRepository.deleteById(id);
	}
}
