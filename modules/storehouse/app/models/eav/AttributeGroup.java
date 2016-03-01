package models.eav;

import models.AbstractModel;

public class AttributeGroup extends AbstractModel {

	private long id;
	private long defaultId;
	private String groupName;
	private int sortOrder;
	private Long attributeSetId;
	private AttributeSet attributeSet;
//	private Set<EAV_EntityAttribute> entityAttributes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDefaultId() {
		return defaultId;
	}

	public void setDefaultId(long defaultId) {
		this.defaultId = defaultId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getAttributeSetId() {
		return attributeSetId;
	}

	public void setAttributeSetId(Long attributeSetId) {
		this.attributeSetId = attributeSetId;
	}

	public AttributeSet getAttributeSet() {
		return attributeSet;
	}

	public void setAttributeSet(AttributeSet attributeSet) {
		this.attributeSet = attributeSet;
	}
/*
	public Set<EAV_EntityAttribute> getEntityAttributes() {
		return entityAttributes;
	}

	public void setEntityAttributes(Set<EAV_EntityAttribute> entityAttributes) {
		this.entityAttributes = entityAttributes;
	}

*/
	
	
	
	
}
