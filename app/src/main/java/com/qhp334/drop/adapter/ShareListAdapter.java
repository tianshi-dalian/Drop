package com.qhp334.drop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.activity.ShareDetailActivity;
import com.qhp334.drop.bean.Share;
import com.qhp334.drop.fragment.viewHolder.ShareViewHolder;
import com.qhp334.drop.service.Const;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/3/30.
 */

public class ShareListAdapter extends RecyclerView.Adapter {
    private List<Share> shareList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;
//    List items;

    public ShareListAdapter(Context context,List<Share> shareList){
        this.context = context;
        this.shareList.addAll(shareList);
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_share_list,parent,false);
        ShareViewHolder viewHolder = new ShareViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShareViewHolder viewHolder = (ShareViewHolder) holder;
        final Share share = shareList.get(position);

        final String shareTitle =share.getSdetail();
        final String shareuName = share.getUname();
        final String shareCreatetime = share.getCreatetime();

        final String sharePic = share.getSmedia();
        final String shareUpic = share.getPic();

        viewHolder.shareTitleView.setText(shareCreatetime);
        viewHolder.shareTitleView.setText(shareTitle);
        viewHolder.uNameView.setText(shareuName);
        if (!sharePic.equals(""))
            Picasso.with(context).load(Const.Share_Url+sharePic).into(viewHolder.imageView);
        if (shareUpic.equals("")){
            Picasso.with(context).load(R.mipmap.ic_launcher_round).into(viewHolder.uPicView);
        }else {
            Picasso.with(context).load(Const.User_URL+shareUpic).into(viewHolder.uPicView);
        }
//        if (!shareUpic.equals(""))
//            Picasso.with(context).load(Const.User_URL+shareUpic).into(viewHolder.uPicView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击进入", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ShareDetailActivity.class);
                intent.putExtra("sid",share);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shareList.size();
    }

}
