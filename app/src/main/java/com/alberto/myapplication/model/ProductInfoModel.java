package com.alberto.myapplication.model;

public class ProductInfoModel {

    private String image;
    private int linioPlusLevel;
    private String conditionType;
    private boolean freeShipping;
    private boolean imported;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLinioPlusLevel() {
        return linioPlusLevel;
    }

    public void setLinioPlusLevel(int linioPlusLevel) {
        this.linioPlusLevel = linioPlusLevel;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}
