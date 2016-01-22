package services;

import models.Stock;
import play.db.jpa.JPA;
import repository.Repository;

import javax.inject.Inject;

public class StockServiceImpl implements StockService {

    Repository<Stock> repository;

    @Inject
    StockServiceImpl(Repository<Stock> repository) {
        this.repository = repository;
    }

    @Override
    public void save(Stock stock) {
        if(stock.getId() == null) JPA.em().persist(stock);
        else JPA.em().merge(stock);
    }

}
