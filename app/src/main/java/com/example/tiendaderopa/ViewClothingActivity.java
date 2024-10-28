package com.example.tiendaderopa;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ViewClothingActivity extends AppCompatActivity {

    private ImageView clothingImage;
    private TextView clothingCode, clothingName, clothingSize, clothingPrice, clothingStock;
    private Button btnPurchase, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clothing);

        // Inicializar elementos de la UI
        clothingImage = findViewById(R.id.clothingImage);
        clothingCode = findViewById(R.id.clothingCode);
        clothingName = findViewById(R.id.clothingName);
        clothingSize = findViewById(R.id.clothingSize);
        clothingPrice = findViewById(R.id.clothingPrice);
        clothingStock = findViewById(R.id.clothingStock);
        btnPurchase = findViewById(R.id.btnPurchase);
        btnBack = findViewById(R.id.btnBack);

        // Obtener índice de la prenda del intent
        int clothingIndex = getIntent().getIntExtra("clothingIndex", -1);
        if (clothingIndex != -1) {
            Clothing clothing = ClothingManager.getInstance().getClothingList().get(clothingIndex);

            // Verificar que la prenda no sea nula
            if (clothing != null) {
                // Rellenar detalles de la prenda
                clothingImage.setImageURI(Uri.parse(clothing.getImageUri()));
                clothingCode.setText("Código: " + clothing.getCode());
                clothingName.setText("Nombre: " + clothing.getName());
                clothingSize.setText("Talla: " + clothing.getSize());
                clothingPrice.setText("Precio: S/" + clothing.getPrice());
                clothingStock.setText("Stock: " + clothing.getStock());
            } else {
                Toast.makeText(this, "Prenda no encontrada", Toast.LENGTH_SHORT).show();
                finish(); // Cerrar actividad si la prenda es nula
            }
        } else {
            Toast.makeText(this, "Índice de prenda inválido", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Evento de clic para comprar (implementar lógica según sea necesario)
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewClothingActivity.this, "Compra exitosa", Toast.LENGTH_SHORT).show();
            }
        });

        // Evento de clic para volver
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
