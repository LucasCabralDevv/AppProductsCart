package com.lucascabral.appdesafionexaas.model;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    private String name;

    private Integer quantity;

    @SerializedName("stock")
    private Integer stock;

    @SerializedName("image_url")
    private String imageUrl;

    private Double price;
    private Double tax;
    private Double shipping;
    private String description;

    public ProductResponse() {
    }

    public ProductResponse(String name, Integer quantity, Integer stock, String imageUrl, Double price, Double tax, Double shipping, String description) {
        this.name = name;
        this.quantity = quantity;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.price = price;
        this.tax = tax;
        this.shipping = shipping;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTax() {
        return tax;
    }

    public Double getShipping() {
        return shipping;
    }

    public String getDescription() {
        return description;
    }
}



