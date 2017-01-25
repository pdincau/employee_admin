package employee;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class GreetingsController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to the XPeppers Training on Microservices!";
    }

}