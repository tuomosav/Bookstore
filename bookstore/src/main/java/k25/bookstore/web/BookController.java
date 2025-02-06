package k25.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.CategoryRepository;
import k25.bookstore.domain.Book;




@Controller
public class BookController {

    @Autowired
    private BookRepository brepository;

    @Autowired
    private CategoryRepository crepository;

    //Show all books
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
    
    //Add new book
    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
    
    //Save new book
    @PostMapping("/save")
    public String save(Book book) {
        brepository.save(book);
        return "redirect:booklist";
    }
    
    //Delete book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        brepository.deleteById(bookId);
        return "redirect:../booklist";
    }

    //Edit book
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", brepository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }
    
}
    