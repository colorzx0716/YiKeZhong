package com.bawie.yikezhong.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.bean.HotVideosBean;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 热门视频的适配类
 */

public class MyFg31Adapter extends XRecyclerView.Adapter<MyFg31Adapter.MyFg31ViewHolder>{


    private final Context context;
    private final List<HotVideosBean.DataBean> data;

    public MyFg31Adapter(Context context, List<HotVideosBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyFg31ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fg31_list_item, null);
        MyFg31ViewHolder holder = new MyFg31ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyFg31ViewHolder holder, int position) {

       List<Integer> heightList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int height = new Random().nextInt(400) + 200;//[200,300)的随机数
            heightList.add(height);
        }

       ViewGroup.LayoutParams params = holder.fg31_list_id.getLayoutParams();
        params.height=heightList.get(position);
        holder.fg31_list_id.setLayoutParams(params);

        View player = View.inflate(context, R.layout.simple_player_view_player, holder.fg31_list_id);
        String s = data.get(position).videoUrl.replaceAll("https://www.zhaoapi.cn", "http://120.27.23.105");

        PlayerView playerView = new PlayerView((Activity) context,player)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(s)
                .startPlay();

        //条目点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyFg31ViewHolder extends RecyclerView.ViewHolder{

        private final RelativeLayout fg31_list_id;

        public MyFg31ViewHolder(View itemView) {
            super(itemView);
            fg31_list_id = itemView.findViewById(R.id.fg31_list_id);
        }
    }

}
