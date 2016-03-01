package models.eav;

import models.AbstractModel;
import org.joda.time.DateTime;

public class EntityEvent extends AbstractModel {

    private Long id;
    private DateTime createTime;
    private DateTime modifyTime;
    private DateTime startTime;
    private DateTime expectedEndTime;
    private DateTime endTime;
    private String name;
    private String text;
    private Double costNetto;
    private Double costBrutto;
    private Long supplierId;
//    private Supplier supplier;
    private Long eventTypeId;
    private EntityEventType eventType;
    private String eventTypeDiscriminator;
    private Long entityId;
    private Entity entity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(DateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public DateTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(DateTime expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Double getCostNetto() {
        return costNetto;
    }

    public void setCostNetto(Double costNetto) {
        this.costNetto = costNetto;
    }

    public Double getCostBrutto() {
        return costBrutto;
    }

    public void setCostBrutto(Double costBrutto) {
        this.costBrutto = costBrutto;
    }

    public String getEventTypeDiscriminator() {
        return eventTypeDiscriminator;
    }

    public void setEventTypeDiscriminator(String eventTypeDiscriminator) {
        this.eventTypeDiscriminator = eventTypeDiscriminator;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public EntityEventType getEventType() {
        return eventType;
    }

    public void setEventType(EntityEventType eventType) {
        this.eventType = eventType;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
