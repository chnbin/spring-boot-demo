package com.chnbin.springbootdemo.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
