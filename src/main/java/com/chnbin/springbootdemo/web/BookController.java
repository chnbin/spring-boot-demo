package com.chnbin.springbootdemo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chnbin.springbootdemo.domain.Book;
import com.chnbin.springbootdemo.service.BookService;

@Controller
public class BookController {
	
	// 注入Service
	@Autowired
	private BookService bookService;

	/**
	 * Get list of all books.
	 * @param model
	 * @return
	 */
	@GetMapping("/books")
	public String list(Model model,
			           @PageableDefault(size=5, sort ={"id"}, direction = Direction.DESC) Pageable pageable) {
		// 利用@PageableDefault來定義一些參數，這可以防止直接在網頁?page=-1這種狀況
		Page<Book> pages = bookService.findAllByPage(pageable);
		model.addAttribute("pages", pages);
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
		// 查不到id時的處置
		// TODO Clean code below
		Optional<Book> book = bookService.findBookById(id);
		if (book.isPresent()) {
			model.addAttribute("book", book.get());
		} else {
			model.addAttribute("book", new Book());
		}
		return "book";
	}
	/**
	 * Jump to input page
	 * @return
	 */
	@GetMapping("/books/input")
	public String insertPage(Model model) {
		model.addAttribute("book", new Book());
		return "input";
	}
	
	/**
	 * Submit a book
	 * @param book
	 * @return
	 * 
	 * POST ---> Redirect ---> GET /books
	 * Two responses.
	 * Model can exist within one response.
	 * So we use RedirectAttributes.
	 */
	@PostMapping("/books")
	public String post(Book book, final RedirectAttributes attr) {
		Book bookReturn = bookService.save(book);
		if (bookReturn != null) { attr.addAttribute("message", bookReturn.getName() + " inserted.");}
		return "redirect:/books";
	}

	/**
	 * Redirect to update page
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/books/input/{id}")
	public String updatePage(@PathVariable long id, Model model) {
		Optional<Book> book = bookService.findBookById(id);
		if (book.isPresent()) {
			model.addAttribute("book", book.get());
		} else {
			model.addAttribute("book", new Book());
		}
		return "input";
	}
}
