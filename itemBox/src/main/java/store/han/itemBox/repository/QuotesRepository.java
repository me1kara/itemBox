package store.han.itemBox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.han.itemBox.entity.Quotes;

public interface QuotesRepository extends JpaRepository<Quotes, Long> {

}
