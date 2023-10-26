package store.han.itemBox.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.han.itemBox.entity.LocalFood;
import store.han.itemBox.repository.LocalFoodRepository;

import java.util.List;

@Service
@Transactional
public class LocalFoodService {
    private LocalFoodRepository localFoodRepository;
    @Autowired
    public LocalFoodService(LocalFoodRepository localFoodRepository){
        this.localFoodRepository = localFoodRepository;
    }

    public List<LocalFood> findAll(){
        return localFoodRepository.findAll();
    }
    public List<String> findFoodByRegion(String region){
        return localFoodRepository.findFoodsByRegion(region);
    }

    public List<String> findRegions() {
        return localFoodRepository.findRegionsDistinct();
    }
}
