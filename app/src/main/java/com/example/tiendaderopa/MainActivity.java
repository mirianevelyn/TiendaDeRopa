package com.example.tiendaderopa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button btnAddClothing, btnEditClothing;
    private RecyclerView recyclerViewClothing;
    private ClothingAdapter clothingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddClothing = findViewById(R.id.btnAddClothing);
        btnEditClothing = findViewById(R.id.btnEditClothing);
        recyclerViewClothing = findViewById(R.id.recyclerViewClothing);

        // Setup RecyclerView
        recyclerViewClothing.setLayoutManager(new LinearLayoutManager(this));
        updateClothingList();

        // Button to Add Clothing
        btnAddClothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddClothingActivity.class);
                startActivity(intent);
            }
        });

        // Button to Edit Clothing (optional functionality)
        btnEditClothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Optional: You can implement editing functionality here
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update clothing list after returning from AddClothingActivity
        updateClothingList();
    }

    private void updateClothingList() {
        List<Clothing> clothingList = ClothingManager.getInstance().getClothingList();
        clothingAdapter = new ClothingAdapter(clothingList, this);
        recyclerViewClothing.setAdapter(clothingAdapter);
    }
}
