package models.eav;

import models.AbstractModel;

import java.util.Set;

public class AttributeSet extends AbstractModel {

    private long id;
    private String setName;
    private long entityTypeId;
    private EntityType entityType;
    private Set<AttributeGroup> attributeGroups;
//    private Set<EAV_EntityAttribute> entityAttributes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }


    public long getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Set<AttributeGroup> getAttributeGroups() {
        return attributeGroups;
    }

    public void setAttributeGroups(Set<AttributeGroup> attributeGroups) {
        this.attributeGroups = attributeGroups;
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
