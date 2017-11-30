package com.bawie.yikezhong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.yikezhong.base.BaseActivity;
import com.bawie.yikezhong.base.BasePresenter;
import com.bawie.yikezhong.fragment.Fragment1;
import com.bawie.yikezhong.fragment.Fragment2;
import com.bawie.yikezhong.fragment.Fragment3;
import com.bawie.yikezhong.fragment.LeftFragment;
import com.bawie.yikezhong.fragment.RightFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.kson.slidingmenu.SlidingMenu;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ImageView main_touxiang;
    private ImageView main_xie;
    private TextView main_head_tv;
    private ImageView main_tuijian;
    private TextView main_tv_tuijian;
    private ImageView main_duanzi;
    private TextView main_tv_duanzi;
    private ImageView main_shipin;
    private TextView main_tv_shipin;
    private SlidingMenu menu;


    @Override
    public List<BasePresenter> initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //手动隐藏标题
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        //头像
        main_touxiang = findViewById(R.id.main_touxiang);
        //发表文章
        main_xie = findViewById(R.id.main_xie);
        //随意更改的标题文字
        main_head_tv = findViewById(R.id.main_head_tv);

        //推荐
        main_tuijian = findViewById(R.id.main_tuijian);
        main_tv_tuijian = findViewById(R.id.main_tv_tuijian);

        //段子
        main_duanzi = findViewById(R.id.main_duanzi);
        main_tv_duanzi = findViewById(R.id.main_tv_duanzi);

        //视频
        main_shipin = findViewById(R.id.main_shipin);
        main_tv_shipin = findViewById(R.id.main_tv_shipin);

            Glide.with(this).load(R.mipmap.touxiang).asBitmap().centerCrop().into(new BitmapImageViewTarget(main_touxiang){
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(MainActivity.this.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    main_touxiang.setImageDrawable(circularBitmapDrawable);
                }
            });



        //点击事件
        main_touxiang.setOnClickListener(this);
        main_xie.setOnClickListener(this);

        //底下三个方块点击事件
        main_tuijian.setOnClickListener(this);
        main_duanzi.setOnClickListener(this);
        main_shipin.setOnClickListener(this);

        //默认是第一页面
        main_tuijian.setImageResource(R.mipmap.tuijian2);
        main_tv_tuijian.setTextColor(Color.parseColor("#33ccff"));
        getSupportFragmentManager().beginTransaction().replace(R.id.fg_layout,new Fragment1()).commit();
        main_head_tv.setText("推荐");

        //左右侧拉
        initMenu();

    }

    /**
     * 左右侧拉
     */
    private void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMenu(R.layout.left_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu,new LeftFragment()).commit();


        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);

       menu.setSecondaryMenu(R.layout.right_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu,new RightFragment()).commit();

        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_touxiang:
                //侧拉
                menu.showMenu();

                break;
            case R.id.main_xie:
                //写文章
                Intent intent = new Intent(MainActivity.this,XieActivity.class);
                startActivity(intent);

                break;
            case R.id.main_tuijian://点击推荐
                main_head_tv.setText("推荐");
                main_tuijian.setImageResource(R.mipmap.tuijian2);
                main_tv_tuijian.setTextColor(Color.parseColor("#33ccff"));
                getSupportFragmentManager().beginTransaction().replace(R.id.fg_layout,new Fragment1()).commit();

                main_duanzi.setImageResource(R.mipmap.duanzi1);
                main_tv_duanzi.setTextColor(Color.parseColor("#999999"));
                main_shipin.setImageResource(R.mipmap.shipin1);
                main_tv_shipin.setTextColor(Color.parseColor("#999999"));

                break;
            case R.id.main_duanzi://点击段子
                main_head_tv.setText("段子");
                main_duanzi.setImageResource(R.mipmap.duanzi2);
                main_tv_duanzi.setTextColor(Color.parseColor("#33ccff"));
                getSupportFragmentManager().beginTransaction().replace(R.id.fg_layout,new Fragment2()).commit();

                main_tuijian.setImageResource(R.mipmap.tuijian1);
                main_tv_tuijian.setTextColor(Color.parseColor("#999999"));
                main_shipin.setImageResource(R.mipmap.shipin1);
                main_tv_shipin.setTextColor(Color.parseColor("#999999"));

                break;
            case R.id.main_shipin://点击视频
                main_head_tv.setText("视频");
                main_shipin.setImageResource(R.mipmap.shipin2);
                main_tv_shipin.setTextColor(Color.parseColor("#33ccff"));
                getSupportFragmentManager().beginTransaction().replace(R.id.fg_layout,new Fragment3()).commit();

                main_duanzi.setImageResource(R.mipmap.duanzi1);
                main_tv_duanzi.setTextColor(Color.parseColor("#999999"));
                main_tuijian.setImageResource(R.mipmap.shipin1);
                main_tv_tuijian.setTextColor(Color.parseColor("#999999"));

                break;

        }
    }


}
