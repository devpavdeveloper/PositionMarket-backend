package by.psu.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    //добавить сюда все url, которые не идут на api
    @RequestMapping(value = {"/catalog", "/auth", "/panel/attractions", "/cart"})
    public String redirect() {
        return "forward:/";
    }
}
