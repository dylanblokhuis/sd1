package com.novi.sd1.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @author Dylan Blokhuis
 * @date 20-5-2020
 * Leerlijn: Software Development Praktijk 1
 */
public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> imagePaths;

    public ImageListAdapter(@NonNull Context context, ArrayList<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        Glide
            .with(context)
            .load(imagePaths.get(position))
            .into(imageView);

        return imageView;
    }
}
