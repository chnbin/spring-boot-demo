package com.chnbin.springbootdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	
	@GetMapping("/books")
	public String list() {
		// 傳回字串books，然後會去template下找一個叫做books的模板
		return "books";
	}
}
