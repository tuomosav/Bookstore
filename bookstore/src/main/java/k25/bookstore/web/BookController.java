package k25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BookController {

    @GetMapping("/index")
    public String book(Model model) {
        System.out.println("Tämä on kirjakauppa.");
        return "index";
    }
    
}
