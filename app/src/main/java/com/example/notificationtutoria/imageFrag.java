package com.example.notificationtutoria;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class imageFrag extends Fragment {

    public  static  ImagesCustomAdapter imagesCustomAdapter;

    public imageFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_image, container, false);
        RecyclerView recyclerView;
        recyclerView=v.findViewById(R.id.imageRec);

        imagesCustomAdapter   =new ImagesCustomAdapter(getContext(),MainActivity.list);
      recyclerView.setAdapter(imagesCustomAdapter);
      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        return v;
    }


}