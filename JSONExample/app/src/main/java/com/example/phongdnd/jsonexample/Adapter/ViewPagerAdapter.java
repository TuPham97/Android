package com.example.phongdnd.jsonexample.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phongdnd.jsonexample.R;

public class ViewPagerAdapter extends PagerAdapter {
    Activity activity;
    int[] images;
    LayoutInflater inflater;
    public ViewPagerAdapter(Activity activity,int[] images)
    {
        this.activity = activity;
        this.images = images;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View)object;
        ((ViewPager) container).removeView(view);
        view = null;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item,container,false);
        ImageView imageView;
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        imageView.setMinimumHeight(height);
        imageView.setMaxWidth(width);
        imageView.setImageResource(images[position]);
        container.addView(itemView);
        return  itemView;
    }
}
