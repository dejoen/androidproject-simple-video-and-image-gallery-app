package com.example.notificationtutoria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class EditImageActivity extends AppCompatActivity {
 ImageView images;
 int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        images=findViewById(R.id.imageView2);
        String imageUrl=getIntent().getStringExtra("imageUri");
        position=getIntent().getIntExtra("imagePos",-1);
        Uri uri=Uri.parse(imageUrl);
        images.setImageURI(uri);
        Toast.makeText(this, String.valueOf(uri), Toast.LENGTH_SHORT).show();

        images.setOnClickListener(view -> deleteImage(imageUrl));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteImage(String imageUri){
        Uri collections;
        String[] projections={MediaStore.Images.ImageColumns._ID,
        MediaStore.Images.ImageColumns.DATA};
        String selections=MediaStore.Images.ImageColumns.DATA + "=?";
        String[] selectionsArgs={imageUri};
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
            collections=MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        }else{
            collections=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(collections,projections,selections,selectionsArgs,null);

        assert cursor!=null;
        if(cursor.moveToFirst()){
            int image=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int imageId=cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID);
            Toast.makeText(this, "yes valid"+cursor.getString(image)+
                    "\n"+cursor.getString(imageId), Toast.LENGTH_SHORT).show();
            Uri uri= ContentUris.withAppendedId(collections, imageId);

                contentResolver.delete(collections,selections,selectionsArgs);
                MainActivity.list.remove(position);
                imageFrag.imagesCustomAdapter.notifyDataSetChanged();

        }
        cursor.close();
    }
}