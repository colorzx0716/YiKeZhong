package com.bawie.yikezhong.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.bean.VideosBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import java.util.List;

/**
 *
 */

public class MyFg11Adapter extends RecyclerView.Adapter<MyFg11Adapter.MyFg11ViewHolder>{

    private Context context;
    private List<VideosBean.DataBean> data;//数据

    private MyFg11ViewHolder holder;

    public MyFg11Adapter(Context context, List<VideosBean.DataBean> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public MyFg11ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到item的布局
        View view= LayoutInflater.from(context).inflate(R.layout.fg11_list_item,parent,false);

        holder = new MyFg11ViewHolder(view);

        return holder;//将布局设置给holder
    }

    @Override
    public void onBindViewHolder(final MyFg11ViewHolder holder, int position) {

        String videoUrl = data.get(position).videoUrl;//视频地址
        String icon = data.get(position).user.icon;//封面

        View player = View.inflate(context, R.layout.simple_player_view_player, holder.fg11_list_player);
       String replace = videoUrl.replace("https://www.zhaoapi.cn","http://120.27.23.105");

        PlayerView playerView = new PlayerView((Activity) context,player)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(replace)
                .startPlay();


        //头像
        if(icon != null){
            Glide.with(context).load(data.get(position).user.icon).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.fg11_list_touxiang){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.fg11_list_touxiang.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

        //昵称
        Object nickname = data.get(position).user.nickname;
        if(nickname != null){
            holder.fg11_list_nicheng.setText((CharSequence) nickname);
        }

        //日期
        String createTime = data.get(position).createTime;
        if(createTime != null){
            holder.fg11_list_date.setText(createTime);
        }


        //描述
        String workDesc = data.get(position).workDesc;
        if(workDesc != null){
            holder.fg11_list_des.setText(workDesc);
        }

       /* boolean setUp = holder.fg11_player.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(icon).into(holder.fg11_player.thumbImageView);
        }*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyFg11ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout fg11_list_player;
        private final ImageView fg11_list_touxiang;//头像
        private final TextView fg11_list_nicheng,fg11_list_date,fg11_list_des;//昵称

        public MyFg11ViewHolder(View itemView) {
            super(itemView);
            fg11_list_player = itemView.findViewById(R.id.fg11_list_player);
            fg11_list_touxiang = itemView.findViewById(R.id.fg11_list_touxiang);
            fg11_list_nicheng = itemView.findViewById(R.id.fg11_list_nicheng);
            fg11_list_date = itemView.findViewById(R.id.fg11_list_date);
            fg11_list_des = itemView.findViewById(R.id.fg11_list_des);
        }

    }

}
