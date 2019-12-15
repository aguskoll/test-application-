package com.example.testapp.presentation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapp.data.entity.Laptop;
import com.example.testapp.R;

import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder> {

    private List<Laptop> items;
    private listener onClick;

    public LaptopAdapter(List<Laptop> items, listener onClick) {
        this.items = items;
        this.onClick = onClick;
    }

    public void updateList(List<Laptop> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(LaptopViewHolder holder, int position) {
        Laptop laptop = items.get(position);

        holder.txtLaptopTitle.setText(laptop.getTitle());
        holder.txtLaptopDescription.setText(laptop.getDescription());
        holder.bindListener(onClick, laptop);
        holder.bindImage(laptop);
    }

    @NonNull
    @Override
    public LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LaptopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.laptop_list_item, parent, false));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class LaptopViewHolder extends RecyclerView.ViewHolder {
        TextView txtLaptopTitle;
        TextView txtLaptopDescription;
        ImageView laptopImage;
        ConstraintLayout itemCard;

        LaptopViewHolder(View itemView) {
            super(itemView);
            txtLaptopTitle = itemView.findViewById(R.id.textView_item_list_title);
            laptopImage = itemView.findViewById(R.id.imageView_item_list_image_thumbnail);
            txtLaptopDescription = itemView.findViewById(R.id.textView_item_list_description);
            itemCard = itemView.findViewById(R.id.laptop_card);
        }

        void bindListener(listener listener, final Laptop laptop) {
            itemCard.setOnClickListener(v -> listener.onClick(laptop));
        }

        void bindImage(Laptop laptop) {
            Log.d("image ",laptop.getImageUrl());
            Glide.with(itemView.getContext())
                    .asBitmap()
                    .load(laptop.getImageUrl())
                    .centerCrop()
                    .into(laptopImage);
        }
    }

    public interface listener {
        void onClick(Laptop laptop);
    }
}
