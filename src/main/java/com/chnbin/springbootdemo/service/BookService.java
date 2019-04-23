package com.chnbin.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public Page<Book> findAllByPage() {
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = PageRequest.of(1, 5, sort);
		return bookRepository.findAll(pageable);
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
	/*
	 * 自定義查詢，根據作者名稱查書單列表
	 */
	public List<Book> queryBooksByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}
	
	public List<Book> queryBooksByAuthorAndStatus(String author, int status) {
		return bookRepository.findByAuthorAndStatus(author, status);
	}
	
	public List<Book> findByJPQL(int len) {
		return bookRepository.findByJPQL(len);
	}
	
	@Transactional // 納入交易（事務）管理
	public int updateByJPQL(int status, long id) {
		return bookRepository.updateByJPQL(status, id);
	}
	
}
