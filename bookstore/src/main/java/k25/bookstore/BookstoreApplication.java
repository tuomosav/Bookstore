package k25.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("Java", "Tuomo Savolainen", "1234-5678", 2025, 55.55));
			//repository.save(new Book());
			//repository.save(new Book());

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

			log.info("haetaan kirjoja nimellä");
			for (Book book : repository.findByTitle("Java")) {
				log.info(book.toString());
			}
		
		};
	}
}
