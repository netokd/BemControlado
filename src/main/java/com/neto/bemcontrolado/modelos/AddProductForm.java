package com.neto.bemcontrolado.modelos;

public class AddProductForm {
    private String name;
    private String description;
    private String labelCode;
    private Integer categoryId; // Usaremos o ID da categoria selecionada
    private Integer inventoryId; // Usaremos o ID do inventário

    public AddProductForm(String name, String description, String labelCode, Integer categoryId, Integer inventoryId) {
        this.name = name;
        this.description = description;
        this.labelCode = labelCode;
        this.categoryId = categoryId;
        this.inventoryId = inventoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
}
