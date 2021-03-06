package com.chnbin.springbootdemo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chnbin.springbootdemo.domain.Book;
import com.chnbin.springbootdemo.service.BookService;

@RestController
@RequestMapping("/api/v1/")
public class BookApp {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public Page<Book> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		// return bookService.findAll();
		Sort sort = new Sort(Direction.DESC, "id");
		return bookService.findAllByPage(PageRequest.of(page, size, sort));
	}
	
	@PostMapping("/books")
	public Book post(@RequestParam String name,
	         @RequestParam String author,
	         @RequestParam String description,
	         @RequestParam int status) {
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setStatus(status);

		return bookService.save(book);
		
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> getBookById(@PathVariable long id) {
		return bookService.findBookById(id);
	}
	
	@PutMapping("/books") 
		public Book update(@RequestParam long id,
					 @RequestParam String name,
			         @RequestParam String author,
			         @RequestParam String description,
			         @RequestParam int status) {
		
		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setStatus(status);

		return bookService.save(book);
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteById(@PathVariable long id) {
		bookService.deleteById(id);
	}
	
	@PostMapping("/books/by")
	public int findByAuthor(@RequestParam int status, @RequestParam long id) {
		// return bookService.queryBooksByAuthor(author);
		// return bookService.queryBooksByAuthorAndStatus(author, status);
		// return bookService.findByJPQL(len);
		return bookService.updateByJPQL(status, id);
	}
}
