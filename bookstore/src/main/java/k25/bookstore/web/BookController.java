package k25.bookstore.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.CategoryRepository;
import k25.bookstore.domain.Book;




@Controller
public class BookController {

    private BookRepository repository;

    private CategoryRepository crepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.repository = bookRepository;
        this.crepository = categoryRepository;
    }

    //Show login
    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    //Show all books
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    
    //Add new book
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
    
    //Save new book
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Errors errors " + book);
            model.addAttribute("book", book);
            model.addAttribute("categories", crepository.findAll());
            return "addbook";
        }
        System.out.println("Save " + book);
        repository.save(book);
        return "redirect:booklist";
    }
    
    //Delete book
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    //Edit book
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", repository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }
    
}
    