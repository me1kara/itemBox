package store.han.itemBox.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import store.han.itemBox.service.QuotesService;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }


}
