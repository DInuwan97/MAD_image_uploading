package com.xyz.kalubo.uploadimg.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ImageInfo.db";

    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES_IMAGE;
        SQL_CREATE_ENTRIES_IMAGE =  " CREATE TABLE " + ImageInfoManager.ImagesUpload.TABLE_NAME + " ( "
                                    + ImageInfoManager.ImagesUpload._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                                    + ImageInfoManager.ImagesUpload.COL_NAME_IMAGE + " BLOB ) ";

        db.execSQL(SQL_CREATE_ENTRIES_IMAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ImageInfoManager.ImagesUpload.TABLE_NAME);
        onCreate(db);
    }


    public boolean addImage(byte[] image){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ImageInfoManager.ImagesUpload.COL_NAME_IMAGE,image);

        long result = db.insert(ImageInfoManager.ImagesUpload.TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor viewPhotos(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor imageData = db.rawQuery("SELECT * FROM " + ImageInfoManager.ImagesUpload.TABLE_NAME,null);
        return imageData;

    }
}
