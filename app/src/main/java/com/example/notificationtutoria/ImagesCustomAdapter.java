package com.example.notificationtutoria;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImagesCustomAdapter extends RecyclerView.Adapter<ImagesCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> images;

    public ImagesCustomAdapter() {
    }

    public ImagesCustomAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.customimagelayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Uri uri=Uri.parse(images.get(position));

  holder.imageView.setImageURI(uri);
  holder.itemView.setOnClickListener(view -> {
      Intent intent=new Intent(context,EditImageActivity.class);
      intent.putExtra("imageUri",images.get(position));
      intent.putExtra("imagePos",position);
      context.startActivity(intent);

  });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
