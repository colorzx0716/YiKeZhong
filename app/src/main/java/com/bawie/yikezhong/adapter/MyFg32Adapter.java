package com.bawie.yikezhong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.bean.VideosBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 视频中的附近
 */

public class MyFg32Adapter extends RecyclerView.Adapter<MyFg32Adapter.MyFg32ViewHolder>{


    private Context context;
    private List<VideosBean.DataBean> data;//数据

   private List<Integer> heightList;//装产出的随机数

    private JCVideoPlayerStandard fg32_player;

    public MyFg32Adapter(Context context, List<VideosBean.DataBean> data) {
        this.context = context;
        this.data = data;

        //记录为每个控件产生的随机高度,避免滑回到顶部出现空白
        heightList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int height = new Random().nextInt(300) + 100;//[100,300)的随机数
            heightList.add(height);
        }

    }

    @Override
    public MyFg32ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到item的布局
        View view= LayoutInflater.from(context).inflate(R.layout.fg32_list_item,parent,false);
        return new MyFg32ViewHolder(view);//将布局设置给holder
    }

    @Override
    public void onBindViewHolder(MyFg32ViewHolder holder, int position) {

        String videoUrl = data.get(position).videoUrl;
        String icon = data.get(position).user.icon;

        boolean setUp = fg32_player.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(icon).into(fg32_player.thumbImageView);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyFg32ViewHolder extends RecyclerView.ViewHolder{

        public MyFg32ViewHolder(View itemView) {
            super(itemView);
            fg32_player = itemView.findViewById(R.id.fg32_player);
        }
    }

}
