package com.example.notificationtutoria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideosCustomAdapter  extends RecyclerView.Adapter<VideosCustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> videos;
public static String TAG="";
    public VideosCustomAdapter(Context context, ArrayList<String> videos) {
        this.context = context;
        this.videos = videos;
        TAG=context.getClass().getSimpleName();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.customvideoadapter,parent,false);
        return new MyViewHolder(v) ;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Uri uri=Uri.parse(videos.get(position));
        //  holder.videoView.setImageBitmap(getVideoBitmap(videos.get(position)));
        BitmapPool bitmapPool=Glide.get(context).getBitmapPool();
      long microSeconds=6000000;
        RequestOptions options=new RequestOptions().frame(microSeconds);

        Glide.with(context).asBitmap().load(videos.get(position))
                .apply(options).into(holder.videoView);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       ImageView videoView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.videoView);
        }
    }


}
