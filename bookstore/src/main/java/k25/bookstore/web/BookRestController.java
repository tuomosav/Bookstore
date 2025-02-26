package k25.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.CategoryRepository;


@RestController
public class BookRestController {

    private static final Logger log = LoggerFactory.getLogger(BookRestController.class);

    private final BookRepository bookRepository;
    private CategoryRepository cRepository;

    public BookRestController(BookRepository bookRepository, CategoryRepository cRepository) {
        this.bookRepository = bookRepository;
        this.cRepository = cRepository;
    }
    
    //Return list of books
    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        log.info("Fetch and return books");
        return bookRepository.findAll();
    }
    
    //Add new book
    @PostMapping("books")
    Book newBook(@RequestBody Book newBook) {
        log.info("Save new book " + newBook);
        return bookRepository.save(newBook);
    }
    
    //Edit existing book's information
    @PutMapping("books/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
        log.info("Edit book " + editedBook);
        editedBook.setId(id);
        return bookRepository.save(editedBook);
    }

    //Delete existing book
    @DeleteMapping("/books/{id}")
    public Iterable<Book> deleteBook(@PathVariable Long id) {
        log.info("Delete book, id = " + id);
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }

    //Find one book and return it
    @GetMapping("/books/{id}")
    Optional<Book> getBook(@PathVariable Long id) {
        log.info("Find book, id = " + id);
        return bookRepository.findById(id);
    }
    
    //Find book by category
    @GetMapping("/books/category/{name}")
    List<Book> getBookByCategoryName(@PathVariable String name) {
        log.info("Find book, category = " + name);
        return bookRepository.findBookByCategoryName(name);
    }
    
}