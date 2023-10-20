package store.han.itemBox.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/map")
public class MapController {
    @GetMapping("/specialties")
    public String specialties(){
        return "/map/specialties";
    }


}
