package com.example.matdes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

public class SlideShowAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater inflater;
    public int[] images={
            R.drawable.angrybirds,R.drawable.asphalt8,R.drawable.cuttherope,R.drawable.worms3,R.drawable.clashofclans,R.drawable.talkingtom,R.drawable.pou,R.drawable.fruitninja,R.drawable.wheresmywhater
    };

    public SlideShowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=inflater.inflate(R.layout.slideshow_layout,container,false);
        ImageView img=(ImageView) view.findViewById(R.id.imageView_id);
       // img.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(img);
        container.addView(view);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"IMAGE "+(position+1),Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(LinearLayout) object;
    }
}
