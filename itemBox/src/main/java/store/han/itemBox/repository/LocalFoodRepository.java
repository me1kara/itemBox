package store.han.itemBox.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.han.itemBox.entity.LocalFood;

import java.util.List;

public interface LocalFoodRepository extends JpaRepository<LocalFood, Long> {
    @Query("select distinct f.region from LocalFood f")
    List<String> findRegionsDistinct();
    @Query("select f.food from LocalFood f where f.region = :region")
    List<String> findFoodsByRegion(String region);

}
