package controllers;

import com.google.inject.Inject;
import models.Stock;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.StockServiceImpl;

public class CStorehouse extends Controller {

    /*

        READ RESTFULL GOOD PRACTICE !

        - List of stocks
            - user_stock table
            - is_store + store info

        - List of items categories
            - make a tree / subcategories
            - list manager !
            - count items in categories per store

        - Stock - list
            -

     */


    private final StockServiceImpl stockService;

    @Inject
    public CStorehouse(StockServiceImpl stockService){
        this.stockService = stockService;
    }

    @Transactional
    public Result test() {

        Stock stock = new Stock();

        stock.setFullStockName(" nanan stock full name");

        stock.setName(" nanan stock short name");

        stockService.save(stock);

        return ok(" Testing stuff ... "+stock.getId());
    }

    public Result testCreate() {
        return ok(" Testing stuff ... ");
    }

}
