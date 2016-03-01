package models.eav;

import models.AbstractModel;

import javax.persistence.PostLoad;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EntityValues extends AbstractModel {

	public static class EntityValuesPK implements Serializable {
		private long attributeId;
		private long stockId;
		private long entityTypeId;
		private long entityId;

		public long getAttributeId() {
			return attributeId;
		}

		public void setAttributeId(long attributeId) {
			this.attributeId = attributeId;
		}

		public long getStockId() {
			return stockId;
		}

		public void setStockId(long stockId) {
			this.stockId = stockId;
		}

		public long getEntityTypeId() {
			return entityTypeId;
		}

		public void setEntityTypeId(long entityTypeId) {
			this.entityTypeId = entityTypeId;
		}

		public long getEntityId() {
			return entityId;
		}

		public void setEntityId(long entityId) {
			this.entityId = entityId;
		}
	}

	private EntityValuesPK pk;
	private boolean active;
	/*
	private boolean damaged;
	private String damagedDescription;
	*/
	private String type;
	private String attributeCode;
	private String label;
	private String valueString;
	private Object value;

	public static final DateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat dateFormat_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

	@PostLoad
	public void postLoad() {
		this.value = this.valueString;
	}

	public EntityValuesPK getPk() {
		return pk;
	}

	public void setPk(EntityValuesPK pk) {
		this.pk = pk;
	}

	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/*
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
	*/
}
