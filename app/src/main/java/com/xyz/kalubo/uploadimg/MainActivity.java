package com.xyz.kalubo.uploadimg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xyz.kalubo.uploadimg.Database.DBHandler;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    Button btnCamera,btnAdd,btnViewPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewPhotos = (Button) findViewById(R.id.btnViewImages);

        

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });
        
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                Bitmap bitmapPhoto = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmapPhoto.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                byte[] image = outputStream.toByteArray();


                if(image == null){
                    Toast.makeText(MainActivity.this,"Please Add an Image",Toast.LENGTH_SHORT).show();
                }else{

                    DBHandler myDB = new DBHandler(MainActivity.this);
                    boolean isInserted = myDB.addImage(image);

                    if(isInserted == true)
                        Toast.makeText(MainActivity.this,"Successfully Image Added!",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Fail to Add!",Toast.LENGTH_LONG).show();


                }

            }
        });

        btnViewPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ImageList.class);
                startActivity(intent);
            }
        });

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }





}
