package com.chnbin.springbootdemo.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


// 定義一個操作Book的數據層
public interface BookRepository extends JpaRepository<Book, Long>{
	
	// Using JAP to page
	Page<Book> findAll(Pageable pageable);
	
	// 複雜的查詢，這個method是有規律的，會自動產生SQL
	List<Book> findByAuthor(String author);
	
	List<Book> findByAuthorAndStatus(String author, int status);
	
	// Like '%des' '%des%' -> Contains
	// List<Book> findByDescriptionEndsWith(String des);
	
	// 自定義查詢
	// @Query("select b from Book b where length(b.name) > ?1") JPQL
	@Query(value = "select * from book where LENGTH(name) > ?1", nativeQuery = true) // SQL
	List<Book> findByJPQL(int len);
	
	@Modifying
	@Query("update Book b set b.status =?1 where id=?2")
	int updateByJPQL(int status, long id);
}
