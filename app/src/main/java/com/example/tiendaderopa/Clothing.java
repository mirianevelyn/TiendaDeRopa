package com.example.tiendaderopa;
import com.example.tiendaderopa.Clothing;

public class Clothing {
    private String code;
    private String name;
    private String size;
    private String price;
    private String stock;
    private String imageUri; // Cambiado de int a String

    public Clothing(String code, String name, String size, String price, String stock, String imageUri) {
        this.code = code;
        this.name = name;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.imageUri = imageUri;
    }

    // Getters y Setters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }

    public String getImageUri() {
        return imageUri;
    }
}
