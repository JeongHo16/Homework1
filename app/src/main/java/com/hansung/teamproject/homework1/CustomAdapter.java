package com.hansung.teamproject.homework1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junho on 2017-10-14.
 */

public class CustomAdapter extends BaseAdapter{
    private Context mContext;
    private int mResource;
    private ArrayList<MyItem> mData = new ArrayList<MyItem>();

    public CustomAdapter(Context context, int resource, ArrayList<MyItem> data){
        mContext = context;
        mData = data;
        mResource = resource;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mResource, viewGroup, false);
        }

        ImageView Image = (ImageView) view.findViewById(R.id.Item_image);

        TextView Text1 = (TextView) view.findViewById(R.id.Item_name);

        TextView Text2 = (TextView) view.findViewById(R.id.Item_price);

        Image.setImageURI(Uri.parse(mData.get(i).image));

        Text1.setText(mData.get(i).name);

        Text2.setText(mData.get(i).price);

        return view;
    }
}
class MyItem{
    String image;
    String name;
    String price;
    String point;

    public MyItem(String image, String name, String price, String point){
        this.image = image;
        this.name = name;
        this.price = price;
        this.point = point;
    }
}
