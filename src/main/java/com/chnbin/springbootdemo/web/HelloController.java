package com.chnbin.springbootdemo.web;
import java.util.HashMap;
import java.util.Map;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @Controller
@RequestMapping("/api/v1")
public class HelloController {
	// 如果@RequestMapping("/say") 則支援所有請求
    // 而各種相對應的請求都有對應的annotation
	// @PostMapping("/say")
	// @DeleteMapping("/say:)
	// ...
	@RequestMapping(value = "/say", method = RequestMethod.GET)
	public String hello() {
		return "Hello Spring Boot";
	}
	
	@GetMapping("/books")
	public String books() {
		return "Books";
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
	
}
