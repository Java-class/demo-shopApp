package ir.javaclass.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String home1() {
        return "/hello";
    }

    @GetMapping("/hello")
    public String home() {
        return "/hello";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }


}
