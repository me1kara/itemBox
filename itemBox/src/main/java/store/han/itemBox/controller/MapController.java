package store.han.itemBox.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.han.itemBox.entity.LocalFood;
import store.han.itemBox.service.LocalFoodService;

import java.util.List;
import java.util.Map;

@RequestMapping("/map")
@Controller
public class MapController {

    private LocalFoodService localFoodService;
    @Autowired
    public MapController(LocalFoodService localFoodService){
        this.localFoodService = localFoodService;
    }
    @GetMapping(value={"/food/{region}","/food"})
    public String specialties(@PathVariable(required = false, value = "region") String region, Model model) {
        if(region==null){
            region = "서울";
        }
        List<String> localFoods = localFoodService.findFoodByRegion(region);
        if (!localFoods.isEmpty()) {
            model.addAttribute("localFoods", localFoods);
            model.addAttribute("regions", localFoodService.findRegions());
            model.addAttribute("region",region);
        }

        return "/map/localFoods";
    }

}
