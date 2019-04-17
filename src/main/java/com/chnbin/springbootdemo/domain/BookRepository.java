package com.chnbin.springbootdemo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// 定義一個操作Book的數據層
public interface BookRepository extends JpaRepository<Book, Long>{
	
	// 自定義的查詢，這個method是有規律的，會自動產生SQL
	List<Book> findByAuthor(String author);
	
	List<Book> findByAuthorAndStatus(String author, int status);
	
	// List<Book> findByDescriptionContain(String des);
}
