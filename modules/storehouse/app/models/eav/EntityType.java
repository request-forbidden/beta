package models.eav;

import models.AbstractModel;

public class EntityType extends AbstractModel {

    private long id;
    private String entityName;
    private String entityType;
    private String catalogIndex;
    private String commercialIndex;
    private String manufacturer;
    private String description;

    private boolean unique;
	private boolean active;
    private int categoryId;
//    private EntityTypeCategory category;
    private int unitId;
//    private Unit unit;
	private Integer min;
	private Integer max;

	/*
    private Set<DocumentEntityType> documentEntityTypes;
    private Set<EAV_Entity> entities;
    private Set<StockTypeQuantity> stq;
	private Vat vat;
	*/
	private Integer vatId;

	public EntityType() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getCatalogIndex() {
		return catalogIndex;
	}

	public void setCatalogIndex(String catalogIndex) {
		this.catalogIndex = catalogIndex;
	}

	public String getCommercialIndex() {
		return commercialIndex;
	}

	public void setCommercialIndex(String commercialIndex) {
		this.commercialIndex = commercialIndex;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getVatId() {
		return vatId;
	}

	public void setVatId(Integer vatId) {
		this.vatId = vatId;
	}
}
