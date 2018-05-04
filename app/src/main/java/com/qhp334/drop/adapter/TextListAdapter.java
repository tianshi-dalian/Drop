package com.qhp334.drop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.activity.TextDetailActivity;
import com.qhp334.drop.bean.TextBean;
import com.qhp334.drop.fragment.viewHolder.TextViewHolder;
import com.qhp334.drop.service.Const;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/3/30.
 */

public class TextListAdapter extends RecyclerView.Adapter {

    private List<TextBean> textBeanList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    public TextListAdapter(Context context,List<TextBean> textBeanList){
        this.context = context;
        this.textBeanList.addAll(textBeanList);
        layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_sharetext_list,parent,false);
        TextViewHolder viewHolder = new TextViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextViewHolder viewHolder = (TextViewHolder) holder;
        final TextBean textBean = textBeanList.get(position);

        final String textTitle = textBean.getTdetail();
        final String textCreateTime = textBean.getTcreatetime();
        final String textUname = textBean.getUname();

        final String textUpic = textBean.getPic();

        viewHolder.tCreateTimeView.setText(textCreateTime);
        viewHolder.uNameView.setText(textUname);
        viewHolder.tDetailView.setText(textTitle);

        if (textUpic.equals("")){
            Picasso.with(context).load(R.mipmap.ic_launcher_round).into(viewHolder.uPicView);
        }else {
            Picasso.with(context).load(Const.User_URL+textUpic).into(viewHolder.uPicView);
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "进入", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, TextDetailActivity.class);
                intent.putExtra("tid",textTitle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return textBeanList.size();
    }
}
