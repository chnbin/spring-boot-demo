package com.chnbin.springbootdemo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chnbin.springbootdemo.domain.Book;
import com.chnbin.springbootdemo.service.BookService;

@Controller
public class BookController {
	
	// 注入Service
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public String list() {
		// 傳回字串books，模板名字，然後會去template下找一個叫做books的模板
		return "books";
	}
	
	@GetMapping("/books/{id}")
	public String detail(@PathVariable long id, Model model) {
		// 傳值給頁面需要用到model
		// Optional<Book> book = bookService.findBookById(id);
		// model.addAttribute("book", book);
		// 因為回傳是個Optional，因此不能直接放到model裡面，要使用下面的寫法
		// https://stackoverflow.com/questions/43751128/el1008e-property-or-field-timestamp-cannot-be-found-on-object-of-type-java-u
		bookService.findBookById(id).ifPresent(o -> model.addAttribute("book", o));
		return "book";
	}
}
