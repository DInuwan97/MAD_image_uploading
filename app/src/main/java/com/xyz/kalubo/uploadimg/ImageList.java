package com.xyz.kalubo.uploadimg;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.xyz.kalubo.uploadimg.Database.DBHandler;

import java.util.ArrayList;

public class ImageList extends AppCompatActivity {

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);


        gridView = (GridView)findViewById(R.id.gridView);


                DBHandler myDB = new DBHandler(ImageList.this);
                final ArrayList<byte []> imageList = new ArrayList<>();

                Cursor data = myDB.viewPhotos();

                if(data.getCount() == 0){
                    Toast.makeText(ImageList.this,"Empty Table",Toast.LENGTH_LONG).show();
                }else{

                    while(data.moveToNext()) {

                        imageList.add(data.getBlob(1));
                        ImageAdapter imageAdapter = new ImageAdapter(ImageList.this, R.layout.gridimage,imageList);
                        gridView.setAdapter(imageAdapter);

                    }
                }




    }


}
