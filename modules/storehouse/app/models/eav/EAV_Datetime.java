package models.eav;

import org.joda.time.DateTime;


public class EAV_Datetime extends EAV_Value {

    private long id;
    private DateTime value; //  TODO switch to datetime joda
    private Attribute attribute;
    private int attributeId;
    private Entity entity;
    private int entityId;

    @Override
    public void remove() {
    }

    public EAV_Datetime() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getValue() {
        return value;
    }

    public void setValue(DateTime value) {
        this.value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}
