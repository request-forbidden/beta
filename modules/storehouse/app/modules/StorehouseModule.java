package modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import models.Stock;
import play.Logger;
import repository.HibernateRepository;
import repository.Repository;
import services.StockService;
import services.StockServiceImpl;

public class StorehouseModule extends AbstractModule {

    @Override
    protected void configure() {

        Logger.info("Storehouse Module configure binds");

        bind(StockService.class).to(StockServiceImpl.class);

        bind(new TypeLiteral<Repository<Stock>>(){}).to(new TypeLiteral<HibernateRepository<Stock>>() {});

    }

}
