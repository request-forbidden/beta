package models;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Attribute extends AbstractModel {

    private long id;
    private String attributeCode;
    private String attributeModel;
    private String backendModel;
    private String backendType;
    private String frontendModel;
    private String frontendInput;
    private String frontendLabel;
    private String frontendClass;
    private String sourceModel;
    private String note;
    private boolean global;
    private boolean visible;
    private boolean visibleOnGrid;
    private boolean required;
    private boolean userDefined;
    private boolean searchable;
    private boolean filterable;
    private boolean scannable;
    private boolean unique;
    private boolean configurable;
//    private Set<EAV_EntityAttribute> entityAttributes;
    private Integer orderNumber;
    //used for saving
    private String value;
    private long entityId;
    private long entityTypeId;
    //used for adding attributes to entity types
    private boolean selected;

    /*
    public static String DECIMAL = "decimal";
    public static String DATETIME = "datetime";
    public static String BOOLEAN = "boolean";
    public static String INT = "int";
    public static String VARCHAR = "varchar";
    */

    private static ArrayNode backendTypeList = new ArrayNode(JsonNodeFactory.instance);

    static {

        ObjectNode on1 = play.libs.Json.newObject();

        on1.put("value", "decimal");
        on1.put("label", "Liczby zm. prze. (decimal)");

        ObjectNode on2 = play.libs.Json.newObject();

        on2.put("value", "datetime");
        on2.put("label", "Data/Czas (datatime)");

        ObjectNode on3 = play.libs.Json.newObject();

        on3.put("value", "boolean");
        on3.put("label", "Typ logiczny (bool)");

        ObjectNode on4 = play.libs.Json.newObject();

        on4.put("value", "int");
        on4.put("label", "Liczba całkowita (int)");

        ObjectNode on5 = play.libs.Json.newObject();

        on5.put("value", "varchar");
        on5.put("label", "Ciąg znaków (varchar)");

        backendTypeList.add(on1);
        backendTypeList.add(on2);
        backendTypeList.add(on3);
        backendTypeList.add(on4);
        backendTypeList.add(on5);

    }

    public Attribute() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public String getAttributeModel() {
        return attributeModel;
    }

    public void setAttributeModel(String attributeModel) {
        this.attributeModel = attributeModel;
    }

    public String getBackendModel() {
        return backendModel;
    }

    public void setBackendModel(String backendModel) {
        this.backendModel = backendModel;
    }

    public String getBackendType() {
        return backendType;
    }

    public void setBackendType(String backendType) {
        this.backendType = backendType;
    }

    public String getFrontendModel() {
        return frontendModel;
    }

    public void setFrontendModel(String frontendModel) {
        this.frontendModel = frontendModel;
    }

    public String getFrontendInput() {
        return frontendInput;
    }

    public void setFrontendInput(String frontendInput) {
        this.frontendInput = frontendInput;
    }

    public String getFrontendLabel() {
        return frontendLabel;
    }

    public void setFrontendLabel(String frontendLabel) {
        this.frontendLabel = frontendLabel;
    }

    public String getFrontendClass() {
        return frontendClass;
    }

    public void setFrontendClass(String frontendClass) {
        this.frontendClass = frontendClass;
    }

    public String getSourceModel() {
        return sourceModel;
    }

    public void setSourceModel(String sourceModel) {
        this.sourceModel = sourceModel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisibleOnGrid() {
        return visibleOnGrid;
    }

    public void setVisibleOnGrid(boolean visibleOnGrid) {
        this.visibleOnGrid = visibleOnGrid;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isUserDefined() {
        return userDefined;
    }

    public void setUserDefined(boolean userDefined) {
        this.userDefined = userDefined;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isFilterable() {
        return filterable;
    }

    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
    }

    public boolean isScannable() {
        return scannable;
    }

    public void setScannable(boolean scannable) {
        this.scannable = scannable;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isConfigurable() {
        return configurable;
    }

    public void setConfigurable(boolean configurable) {
        this.configurable = configurable;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(long entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static ArrayNode getBackendTypeList() {
        return backendTypeList;
    }

    public static void setBackendTypeList(ArrayNode backendTypeList) {
        Attribute.backendTypeList = backendTypeList;
    }
}
