package store.han.itemBox.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.han.itemBox.entity.Quotes;
import store.han.itemBox.service.QuotesService;

@Controller
@RequestMapping("/quotes")
@Slf4j
public class QuotesController {
    private QuotesService quotesService;
    @Autowired
    public QuotesController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }
    @GetMapping("/random")
    public String getRandom(Model model) {
        Quotes quotes = random().getBody();
        model.addAttribute("quotes",quotes);
        return "/quotes/random";
    }

    @ResponseBody
    @PostMapping("/random")
    public ResponseEntity<Quotes> random(){
        Quotes quotes = quotesService.findRandom();
        return ResponseEntity.ok(quotes);
    }

}
