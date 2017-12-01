package com.bawie.yikezhong.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.bean.UserJoker;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 段子页面的recyclerview的适配器
 */

public class MyFg2Adapter extends XRecyclerView.Adapter<MyFg2Adapter.MyViewHolder>/* implements View.OnClickListener*/ {

    private final Context context;
    private final List<UserJoker.DataBean> data;
    private OnItemClickListener onItemClickListener;

    private LayoutInflater mLayoutInflater;

    //动画
    private int a=0;
    private ObjectAnimator animator;
    private ObjectAnimator fanimator;
    private ObjectAnimator animator1;
    private ObjectAnimator fanimator1;
    private ObjectAnimator animator2;
    private ObjectAnimator fanimator2;
    private ObjectAnimator animator3;
    private ObjectAnimator fanimator3;
    private MyViewHolder holder;


    public MyFg2Adapter(Context context, List<UserJoker.DataBean> data) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        holder = new MyViewHolder(mLayoutInflater.inflate(R.layout.rv_list_item, null));

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.rv_list_nicheng.setText(data.get(position).user.nickname);
        holder.rv_list_date.setText(data.get(position).createTime);
        holder.rv_list_duanzi.setText(data.get(position).content);

        if(data.get(position).imgUrls != null){
            //============嵌套的Recyclerview
            String string = data.get(position).imgUrls.toString();
            System.out.println("string = " + string);
            String[] split = string.split("\\|");
            List<String> imgUrls = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                imgUrls.add(split[i]);
                System.out.println("i = " + split[i]);
            }

            GridLayoutManager layoutManager = new GridLayoutManager(context,3);
            holder.rv_list_rv.setLayoutManager(layoutManager);

            MyFg2sAdapter myFg2sAdapter = new MyFg2sAdapter(context,imgUrls);
            holder.rv_list_rv.setAdapter(myFg2sAdapter);
            //==============
        }

        //将position保存在itemview的Tag中，以便点击时获取
        //holder.itemView.setTag(position);

        //第一次
        Glide.with(context).load(data.get(position).user.icon).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.rv_list_touxiang){
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.rv_list_touxiang.setImageDrawable(circularBitmapDrawable);
            }
        });

        //==========图片动画集合
        initIMG();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Toast.makeText(context, "水波纹~~"+position, Toast.LENGTH_SHORT).show();
            }
        });



    }

    //图片动画集合
    private void initIMG() {
        //===============
        animator = ObjectAnimator.ofFloat(holder.rv_list_jia, "rotation", 0f, 180f);
        animator1 = ObjectAnimator.ofFloat(holder.rv_list_collection, "translationX", 0f,-80f);
        animator2 = ObjectAnimator.ofFloat(holder.rv_list_share, "translationX", 0f,-160f);
        animator3 = ObjectAnimator.ofFloat(holder.rv_list_news, "translationX", 0f,-240f);

        //----缩回时的动画
        fanimator = ObjectAnimator.ofFloat(holder.rv_list_jian, "rotation", 0f, -180f);
        fanimator1 = ObjectAnimator.ofFloat(holder.rv_list_collection, "translationX", -80f,0f);
        fanimator2 = ObjectAnimator.ofFloat(holder.rv_list_share, "translationX", -160f,0f);
        fanimator3 = ObjectAnimator.ofFloat(holder.rv_list_news, "translationX", -240f,0f);

        //-------

        //给伸出动画设置监听
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                holder.rv_list_jia.setImageResource(R.mipmap.rv_list_jian);//动画结束改变图片

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        //给缩回动画设置监听
        fanimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                holder.rv_list_jia.setImageResource(R.mipmap.rv_list_jia);//改变图片
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


        holder.rv_list_jia.setOnClickListener(new View.OnClickListener() {//图片的点击事件
            @Override
            public void onClick(View view) {
                a++;
                if(a%2==1){//第一次点击是实现伸出效果
                    AnimatorSet animSet = new AnimatorSet();//动画集合
                    animSet.play(animator).with(animator1).with(animator2).with(animator3);
                    animSet.setDuration(1000);
                    animSet.start();

                }else{//再点击一次实现缩回效果
                    AnimatorSet animSet1 = new AnimatorSet();//动画集合
                    animSet1.play(fanimator).with(fanimator1).with(fanimator2).with(fanimator3);
                    animSet1.setDuration(1000);
                    animSet1.start();
                }
            }

        });

        //============

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //自动生成的点击方法
   /* @Override
    public void onClick(View view) {
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }*/

    class MyViewHolder extends XRecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView rv_list_touxiang;//头像
        private final TextView rv_list_date;//日期
        private final TextView rv_list_nicheng;//昵称
        private final ImageView rv_list_jia;
        private final RelativeLayout rv_list_collection;
        private final ImageView rv_list_jian;
        private final RelativeLayout rv_list_share;
        private final RelativeLayout rv_list_news;
        //RecyclerView 的多布局
        private final RecyclerView rv_list_rv;
        private final TextView rv_list_duanzi;


        public MyViewHolder(View itemView) {
            super(itemView);
            rv_list_touxiang = itemView.findViewById(R.id.rv_list_touxiang);
            rv_list_date = itemView.findViewById(R.id.rv_list_date);
            rv_list_nicheng = itemView.findViewById(R.id.rv_list_nicheng);
            rv_list_rv = itemView.findViewById(R.id.rv_list_rv);
            //文字段子
            rv_list_duanzi = itemView.findViewById(R.id.rv_list_duanzi);
            //5个图片
            rv_list_jia = itemView.findViewById(R.id.rv_list_jia);//加
            rv_list_collection = itemView.findViewById(R.id.rv_list_collection);//收藏
            rv_list_jian = itemView.findViewById(R.id.rv_list_jian);//减
            rv_list_share = itemView.findViewById(R.id.rv_list_share);//分享
            rv_list_news = itemView.findViewById(R.id.rv_list_news);//消息

            //消息
            rv_list_news.setOnClickListener(this);
            //分享
            rv_list_share.setOnClickListener(this);
            //点赞
            rv_list_collection.setOnClickListener(this);
            //点击头像
            rv_list_touxiang.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rv_list_news:
                    Toast.makeText(context, "要发布消息吗", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rv_list_share:
                    Toast.makeText(context, "要分享段子吗", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rv_list_collection:
                    Toast.makeText(context, "要收藏段子吗", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rv_list_touxiang://段子手们的头像
                    Toast.makeText(context, "点击头像了", Toast.LENGTH_SHORT).show();


                    break;
            }

        }
    }

    //模拟ListView的条目点击事件
    public  interface OnItemClickListener{
        void onItemClick(View view,int position);
    }


}
