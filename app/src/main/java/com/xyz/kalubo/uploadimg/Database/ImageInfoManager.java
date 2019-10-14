package com.xyz.kalubo.uploadimg.Database;

import android.provider.BaseColumns;

public class ImageInfoManager {

    public ImageInfoManager(){

    }

    public static class ImagesUpload implements BaseColumns{

        public static final String TABLE_NAME = "images";
        public static final String COL_NAME_IMAGE = "image";


    }


}
