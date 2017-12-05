package com.bawie.yikezhong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.yikezhong.R;

import java.util.List;


/**
 *左侧的Adapter
 */

public class MyListAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> tvs;
    private final List<Integer> imgs;

    public MyListAdapter(Context context, List<String> tvs, List<Integer> imgs) {
        this.context = context;
        this.tvs = tvs;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return tvs.size();
    }

    @Override
    public Object getItem(int i) {
        return tvs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Zujian zujian;

        if(view == null){

            zujian = new Zujian();
           view = LayoutInflater.from(context).inflate(R.layout.left_list_item, null);
            zujian.left_list_img1 = view.findViewById(R.id.left_list_img1);
            zujian.left_list_img2 = view.findViewById(R.id.left_list_img2);
            zujian.left_list_tv = view.findViewById(R.id.left_list_tv);

            view.setTag(zujian);

        }else{

            zujian = (Zujian) view.getTag();
        }

        zujian.left_list_img1.setImageResource(imgs.get(i));
        zujian.left_list_tv.setText(tvs.get(i));

        return view;

    }

    public final class Zujian{

        public ImageView left_list_img1;
        public ImageView left_list_img2;
        public TextView left_list_tv;

    }



}
