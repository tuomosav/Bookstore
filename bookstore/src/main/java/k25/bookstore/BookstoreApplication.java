package k25.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.Category;
import k25.bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");

			Category category1 = new Category("IT");
			Category category2 = new Category("Fantasia");
			Category category3 = new Category("Scifi");

			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);

			brepository.save(new Book("Java", "Tuomo Savolainen", "1234-5678", 2025, 55.55, category1));
			brepository.save(new Book("Backend", "Tuomo Savolainen", "6789-2345", 2021, 14.95, category1));
			brepository.save(new Book("C++ perusteet", "Kimmo Koodari", "1010-1010", 2005, 19.95, category1));
			brepository.save(new Book("Taru Sormusten Herrasta", "J. R. R. Tolkien", "1337-7331", 1954, 99.95, category2));

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

			log.info("haetaan kirjoja nimellä Java");
			for (Book book : brepository.findByTitle("Java")) {
				log.info(book.toString());
			}
		
		};
	}
}
