package com.example.tiendaderopa;

import java.util.ArrayList;
import java.util.List;

public class ClothingManager {
    private static ClothingManager instance;
    private List<Clothing> clothingList;

    private ClothingManager() {
        clothingList = new ArrayList<>();
        initializeClothingList(); // Llamar al método para inicializar la lista
    }

    public static ClothingManager getInstance() {
        if (instance == null) {
            instance = new ClothingManager();
        }
        return instance;
    }

    public List<Clothing> getClothingList() {
        return clothingList;
    }

    public void addClothing(Clothing clothing) {
        clothingList.add(clothing);
    }

    // Método para inicializar la lista de ropa con productos predeterminados
    private void initializeClothingList() {
        clothingList.add(new Clothing("001", "Camisa Blanca", "M", "49.99", "20", "android.resource://" + "com.example.tiendaderopa" + "/" + R.drawable.shirt_white));
        clothingList.add(new Clothing("002", "Pantalón Azul", "L", "89.99", "15", "android.resource://" + "com.example.tiendaderopa" + "/" + R.drawable.pants_blue));
        clothingList.add(new Clothing("003", "Vestido Rojo", "S", "59.99", "10", "android.resource://" + "com.example.tiendaderopa" + "/" + R.drawable.dress_red));
        clothingList.add(new Clothing("004", "Chaqueta Negra", "XL", "99.99", "5", "android.resource://" + "com.example.tiendaderopa" + "/" + R.drawable.jacket_black));
    }
}
