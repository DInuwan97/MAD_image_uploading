package com.xyz.kalubo.uploadimg;

        import android.content.Context;
        import android.graphics.BitmapFactory;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;

        import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {

    private LayoutInflater layoutInflater;

    public ImageAdapter(Context context, int resource, ArrayList<byte[]> images) {

        super(context,R.layout.gridimage,images);

        this.layoutInflater = LayoutInflater.from(context);

    }

    public View getView(int position, View convertView, ViewGroup parent){
        byte[] image = (byte[]) getItem(position);

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.gridimage,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView2);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));

        return convertView;
    }
}
