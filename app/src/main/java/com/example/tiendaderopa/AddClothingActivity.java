package com.example.tiendaderopa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AddClothingActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int PICK_IMAGE_REQUEST = 101;

    private EditText inputCode, inputName, inputSize, inputPrice, inputStock;
    private ImageView imageClothing;
    private Button btnSave, btnBack, btnSelectImage;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothing);

        // Inicializar campos del formulario
        inputCode = findViewById(R.id.inputCode);
        inputName = findViewById(R.id.inputName);
        inputSize = findViewById(R.id.inputSize);
        inputPrice = findViewById(R.id.inputPrice);
        inputStock = findViewById(R.id.inputStock);
        imageClothing = findViewById(R.id.imageClothing);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
        btnSelectImage = findViewById(R.id.btnSelectImage);

        // Solicitar permisos
        checkPermission();

        // Evento de clic para seleccionar una imagen
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Evento de clic para guardar la prenda
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClothing();
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

    // Método para abrir la galería
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Manejar el resultado de la selección de la imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imageClothing.setImageURI(selectedImageUri); // Establecer la imagen seleccionada
        }
    }

    // Método para guardar la prenda
    private void saveClothing() {
        // Recoger datos
        String code = inputCode.getText().toString();
        String name = inputName.getText().toString();
        String size = inputSize.getText().toString();
        String price = inputPrice.getText().toString();
        String stock = inputStock.getText().toString();

        if (selectedImageUri != null) {
            // Crear un objeto Clothing
            Clothing newClothing = new Clothing(code, name, size, price, stock, selectedImageUri.toString());

            // Añadir a la lista de prendas
            ClothingManager.getInstance().addClothing(newClothing);

            Toast.makeText(AddClothingActivity.this, "Prenda agregada exitosamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Por favor, selecciona una imagen", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para comprobar y solicitar permisos
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
            }
        }
    }

    // Manejar la respuesta de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes acceder a la galería
            } else {
                // Permiso denegado, informa al usuario
                Toast.makeText(this, "Permiso de acceso a la galería denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
