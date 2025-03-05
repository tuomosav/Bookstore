package k25.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.Category;
import k25.bookstore.domain.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookstoreRepoTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Taru Sormusten Herrasta");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("J. R. R. Tolkien");
    }

    @Test
    public void creatNewBook() {
        Category category = new Category("Romaani");
        crepository.save(category);
        Book book = new Book("Testaus", "Teresa Testaaja", "7357-7357", 2025, 73.57, category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

//    @Test
//    public void editNewBook() {
//        List<Book> books = repository.findByTitle("Taru Sormusten Herrasta");
//        Book book = books.get(0);
//        repository.edit(book);
//    }

    @Test
    public void deleteNewBook() {
        List<Book> books = repository.findByTitle("Taru Sormusten Herrasta");
        Book book = books.get(0);
        repository.delete(book);
        List<Book> newBooks = repository.findByTitle("Taru Sormusten Herrasta");
        assertThat(newBooks).hasSize(0);
    }
}
