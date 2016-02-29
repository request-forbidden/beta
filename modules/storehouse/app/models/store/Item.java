package models.store;

import models.AbstractModel;

public class Item extends AbstractModel {

    private Long id;
    private String name;
    private String descr;
    private String info; //additional type info type info
    private String brand;
    private Double priceNetto;
    private Double pricebrutto;
    private Integer vat;
    private Double currentPriceNetto;
    private Double currentPriceBrutto;


}
