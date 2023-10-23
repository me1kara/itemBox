package store.han.itemBox.service;


import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.han.itemBox.entity.Quotes;
import store.han.itemBox.repository.QuotesRepository;

import java.util.List;

@Service
@Transactional public class QuotesService {

    private QuotesRepository quotesRepository;
    @Autowired
    public QuotesService(QuotesRepository quotesRepository){
        this.quotesRepository = quotesRepository;
    }

    public List<Quotes> findAll(){
        return quotesRepository.findAll();
    }
    public Quotes findRandom(){
        Long random = (long)(Math.random()*10)+1;
        return quotesRepository.findById(random).orElseThrow(()->new IllegalArgumentException("DB에서 명언을 찾지 못했습니다"));
    }


}
