package com.example.tiendaderopa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.tiendaderopa.Clothing;

public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder> {

    private List<Clothing> clothingList;
    private Context context;

    public ClothingAdapter(List<Clothing> clothingList, Context context) {
        this.clothingList = clothingList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClothingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_clothing, parent, false);
        return new ClothingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothingViewHolder holder, int position) {
        Clothing clothing = clothingList.get(position);
        holder.clothingName.setText(clothing.getName());

        // Cargar la imagen usando URI
        Uri imageUri = Uri.parse(clothing.getImageUri());

        // Verificar que la URI no sea nula
        if (imageUri != null) {
            holder.clothingImage.setImageURI(imageUri);
        } else {
            holder.clothingImage.setImageResource(R.drawable.placeholder_image); // Imagen de marcador de posición
        }

        // Evento de clic para ver información detallada
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewClothingActivity.class);
                intent.putExtra("clothingIndex", position);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return clothingList.size();
    }

    public static class ClothingViewHolder extends RecyclerView.ViewHolder {
        ImageView clothingImage;
        TextView clothingName;

        public ClothingViewHolder(@NonNull View itemView) {
            super(itemView);
            clothingImage = itemView.findViewById(R.id.clothingImage);
            clothingName = itemView.findViewById(R.id.clothingName);
        }
    }
}
