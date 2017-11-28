package com.bawie.yikezhong.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.yikezhong.R;
import com.bawie.yikezhong.bean.UserJoker;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * 段子页面的recyclerview的适配器
 */

public class MyFg2Adapter extends RecyclerView.Adapter<MyFg2Adapter.MyViewHolder>{

    private final Context context;
    private final List<UserJoker.DataBean> data;

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


    public MyFg2Adapter(Context context, List<UserJoker.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rv_list_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.rv_list_duanzi.setText(data.get(position).content);
        holder.rv_list_nicheng.setText(data.get(position).user.nickname);
        holder.rv_list_date.setText(data.get(position).createTime);

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

        //===============
        animator = ObjectAnimator.ofFloat(holder.rv_list_icon_packup, "rotation", 0f, 180f);
        animator1 = ObjectAnimator.ofFloat(holder.rv_list_copylink1, "translationX", 0f,-80f);
        animator2 = ObjectAnimator.ofFloat(holder.rv_list_shiled1, "translationX", 0f,-160f);
        animator3 = ObjectAnimator.ofFloat(holder.rv_list_icon_report1, "translationX", 0f,-240f);

        //----缩回时的动画
        fanimator = ObjectAnimator.ofFloat(holder.rv_list_icon_packup, "rotation", 0f, -180f);
        fanimator1 = ObjectAnimator.ofFloat(holder.rv_list_copylink1, "translationX", -80f,0f);
        fanimator2 = ObjectAnimator.ofFloat(holder.rv_list_shiled1, "translationX", -160f,0f);
        fanimator3 = ObjectAnimator.ofFloat(holder.rv_list_icon_report1, "translationX", -240f,0f);

       //-------

        //给伸出动画设置监听
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                holder.rv_list_copylink1.setImageResource(R.mipmap.icon_packup);//动画结束改变图片

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
                holder.rv_list_copylink1.setImageResource(R.mipmap.icon_packup);//改变图片
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


        holder.rv_list_icon_open.setOnClickListener(new View.OnClickListener() {//图片的点击事件
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

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView rv_list_touxiang;//头像
        private final TextView rv_list_date,rv_list_duanzi;//日期,段子
        private final TextView rv_list_nicheng;//昵称
        private final ImageView rv_list_copylink1;
        private final ImageView rv_list_icon_open;
        private final ImageView rv_list_icon_packup;
        private final ImageView rv_list_icon_report1;
        private final ImageView rv_list_shiled1;


        public MyViewHolder(View itemView) {
            super(itemView);
            rv_list_touxiang = itemView.findViewById(R.id.rv_list_touxiang);
            rv_list_date = itemView.findViewById(R.id.rv_list_date);
            rv_list_nicheng = itemView.findViewById(R.id.rv_list_nicheng);
            rv_list_duanzi = itemView.findViewById(R.id.rv_list_duanzi);

            //四个图片
            rv_list_copylink1 = itemView.findViewById(R.id.rv_list_copylink1);
            rv_list_icon_open = itemView.findViewById(R.id.rv_list_icon_open);
            rv_list_icon_packup = itemView.findViewById(R.id.rv_list_icon_packup);
            rv_list_icon_report1 = itemView.findViewById(R.id.rv_list_icon_report1);
            rv_list_shiled1 = itemView.findViewById(R.id.rv_list_shiled1);


        }
    }

}
