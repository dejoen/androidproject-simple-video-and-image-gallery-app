package com.example.notificationtutoria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 Button vbutton, ibutton;
 EditText getText;
 int id=1;
 public static ArrayList<String> list,videoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
vbutton=findViewById(R.id.videoesId);
ibutton=findViewById(R.id.imagesbutton);
checkPermission();
  vbutton.setOnClickListener(view -> addFragment(new VideoFrag()));
        ibutton.setOnClickListener(view -> addFragment(new imageFrag()));


    }

    private  void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);

        }else{
         new Thread(new Runnable() {
             @Override
             public void run() {

             }
         }).start();
          list=loadImagesFromMediaStore();
          videoList=loadVideosFromMediaStore();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==2){

            list=loadImagesFromMediaStore();
            videoList=loadVideosFromMediaStore();
        }
    }

    private  void addFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.myFrame,fragment);
       transaction.commit();
    }
    private ArrayList<String> loadVideosFromMediaStore(){
        ArrayList<String> videos=new ArrayList<>();
        Uri collections;

        String[] projections ={MediaStore.Video.VideoColumns.DATA,
                MediaStore.Video.VideoColumns.DATE_ADDED,MediaStore.Video.VideoColumns.TITLE,
                MediaStore.Video.VideoColumns.DATE_ADDED,MediaStore.Video.VideoColumns.DURATION
        };
        String sortOrder=MediaStore.Video.VideoColumns.DATE_ADDED + " DESC";

        ContentResolver contentResolver=getContentResolver();

        Cursor cursor;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
            collections=MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        }else{
            collections=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }

        cursor=contentResolver.query(collections,projections,null,null,sortOrder);
        assert cursor!=null;
        if(cursor.moveToFirst()){
            int videoTitle=cursor.getColumnIndex(MediaStore.Video.VideoColumns.TITLE);
            int videoUrl=cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);

            do{
             videos.add(cursor.getString(videoUrl));
            }while (cursor.moveToNext());
        }
        cursor.close();


        return  videos;
    }
    private ArrayList<String> loadImagesFromMediaStore(){
        ArrayList<String> images=new ArrayList<>();
        Uri collections;

        String[] projections ={MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.DATE_ADDED,MediaStore.Images.ImageColumns.TITLE
        };
        String sortOrder=MediaStore.Images.ImageColumns.DATE_ADDED + " DESC";

        ContentResolver contentResolver=getContentResolver();

        Cursor cursor;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
            collections=MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        }else{
            collections=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        cursor=contentResolver.query(collections,projections,null,null,sortOrder);
        assert cursor!=null;
        if(cursor.moveToFirst()){
            int imageTitle=cursor.getColumnIndex(MediaStore.Images.ImageColumns.TITLE);
            int imageUrl=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            do{
                images.add(cursor.getString(imageUrl));
            }while (cursor.moveToNext());
        }
        cursor.close();


        return  images;

    }

    private void showNotification(int mid,String message){
        RemoteInput remoteInput=new RemoteInput.Builder("reply message")
                .setLabel("reply").build();
        Intent intent=new Intent(this,NotificationReceiverActivity.class);
        PendingIntent Pintent=PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Action action=new NotificationCompat.Action.Builder(android.R.drawable.star_on,"reply",Pintent)
                .addRemoteInput(remoteInput)
                .build();

        Notification notification=new  NotificationCompat.Builder(this,App.channel_Id)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("myNotification")
                .setContentTitle("this is my message")
                .setColor(Color.GREEN)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(message)
                        .addLine("kattt")
                        .setBigContentTitle("messages")
                )
              .addAction(action)

                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.star_on))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(this);
            notificationManager.notify(mid,notification);


    }

}