package k25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.Book;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    //Show all books
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    
    //Add new book
    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }
    
    //Save new book
    @PostMapping("/save")
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }
    
    //Delete book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    //Edit book
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", repository.findById(bookId));
        return "editbook";
    }
    
}
    