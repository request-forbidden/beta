package models;

import org.joda.time.DateTime;

public class Entity extends AbstractModel {

    private long id;
    private DateTime createdAt;
    private DateTime updatedAt;
    private boolean active;
	private boolean damaged;
	private String damagedDescription;
	private DateTime damagedDate;
    private long entityTypeId;
    private long documentId;
    private long stockId;
//    private Stock stock;
//    private EntityType entityType;
//    private Set<Document> documents;
//	private Set<EAV_EntityValues> values;

    public Entity() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDamaged() {
		return damaged;
	}

	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}

	public String getDamagedDescription() {
		return damagedDescription;
	}

	public void setDamagedDescription(String damagedDescription) {
		this.damagedDescription = damagedDescription;
	}

	public DateTime getDamagedDate() {
		return damagedDate;
	}

	public void setDamagedDate(DateTime damagedDate) {
		this.damagedDate = damagedDate;
	}

	public long getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(long entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
}
