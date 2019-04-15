package com.chnbin.springbootdemo.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @Controller
@RequestMapping("/api/v2")
public class HelloController {
	// 自定義屬性：從resources/application.yml下面讀過來的
	@Value("${book.id}")
	private String bookId;
	@Value("${book.name}")
	private String bookName;
	@Value("${book.author}")
	private String author;
	@Value("${book.description}")
	private String description;
	
	// 如果@RequestMapping("/say") 則支援所有請求
    // 而各種相對應的請求都有對應的annotation
	// @PostMapping("/say")
	// @DeleteMapping("/say:)
	// ...
	@RequestMapping(value = "/say", method = RequestMethod.GET)
	public String hello() {
		return "Hello Spring Boot";
	}

	// GET http://localhost:8080/api/v1/books?page=1&size=10
	// @RequestParam(value = "size", defaultValue = "10")是要讓GET http://localhost:8080/api/v1/books?page=1，也就是沒有
	// size時給個預設值10
	@GetMapping("/books")
	public Object getAllBooks(@RequestParam int page, @RequestParam(value = "size", defaultValue = "10") int size) {
		Map<String, Object> book = new HashMap<>();
		book.put("name", "Cujo");
		book.put("author", "Stenphen King");
		Map<String, Object> book2 = new HashMap<>();
		book2.put("name", "Shiki");
		book2.put("author", "Ono Fuyumi");
		
		// 自定義屬性
		Map<String, Object> book3 = new HashMap<>();
		book3.put("id", bookId);
		book3.put("name", bookName);
		book3.put("author", author);
		book3.put("description", description);

		List<Map<String, Object>> contents = new ArrayList<>();
		contents.add(book);
		contents.add(book2);
		contents.add(book3);
		
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("page", page);
		pageMap.put("size", size);
		pageMap.put("content", contents);
		return pageMap;
	}

	// 接受參數，GET http://localhost:8080/api/v1/books/{id}
	// id要對應，如果不要對應的話請用以下寫法
	// public Object getBookById(@PathVariable("id") long bookId) {
	@GetMapping("/books/{id}")
	public Object getBookById(@PathVariable long id) {
		
		System.out.println("id: " + id);
		
		Map<String, Object> book = new HashMap<>();
		book.put("name", "Cujo");
		book.put("author", "Stenphen King");
		return book;
	}
	
	// 接受多個參數，GET http://localhost:8080/api/v1/books/{id}/{usrname}
	// 且userName用正則表達式
	@GetMapping("/books/{id}/{userName:[a-z_]+}")
	public Object getBookById2(@PathVariable long id, @PathVariable String userName) {
		
		System.out.println("id: " + id + " userName: " + userName);
		
		Map<String, Object> book = new HashMap<>();
		book.put("name", "Cujo");
		book.put("author", "Stenphen King");
		book.put("usrname", userName);
		return book;
	}
	
	// GET http://localhost:8080/api/v1/books/
	// 目前沒有服務，使用POSMAN模擬Form input
	@PostMapping("/books")
	public Object post(@RequestParam String name,
			           @RequestParam String author,
			           @RequestParam String userName) {
		Map<String, Object> book = new HashMap<>();
		book.put("name", name);
		book.put("author", author);
		book.put("usrname", userName);
		
		// Write to database or something...
		return book;
	}
	
}
