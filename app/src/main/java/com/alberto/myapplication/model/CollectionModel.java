package com.alberto.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CollectionModel {

    @SerializedName("products")
    @Expose
    public HashMap<String, ProductInfoModel> productValues;

    @SerializedName("name")
    @Expose
    public String name;

    public HashMap<String, ProductInfoModel> getProductValues() {
        return productValues;
    }

    public void setDataValues(HashMap<String, ProductInfoModel> dataValues) {
        this.productValues = dataValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
