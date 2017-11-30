package com.bawie.yikezhong.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawie.yikezhong.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 段子的子布局里面的recyclerview的适配器
 */

public class MyFg2sAdapter extends RecyclerView.Adapter<MyFg2sAdapter.MyFg2sViewHolder>{

    private final Context context;
    private final List<String> imgUrls;
    private LayoutInflater mLayoutInflater;

    public MyFg2sAdapter(Context context, List<String> imgUrls) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.imgUrls = imgUrls;
    }

    @Override
    public MyFg2sViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFg2sViewHolder(mLayoutInflater.inflate(R.layout.fg2s_list_item, null));

    }

    @Override
    public void onBindViewHolder(MyFg2sViewHolder holder, final int position) {

        Glide.with(context)
                .load(imgUrls.get(position))
                .centerCrop()
                .into(holder.fg2s_iv);

       //======图片是否删除
        holder.fg2s_iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(context)
                        .setTitle("是否删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                imgUrls.remove(position);
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消",null);
                ad.show();

                return false;
            }
        });

        //====图片是否删除弹框

    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }

    class MyFg2sViewHolder extends RecyclerView.ViewHolder{

        private final ImageView fg2s_iv;

        public MyFg2sViewHolder(View itemView) {
        super(itemView);
         fg2s_iv = itemView.findViewById(R.id.fg2s_iv);
    }
}

}
