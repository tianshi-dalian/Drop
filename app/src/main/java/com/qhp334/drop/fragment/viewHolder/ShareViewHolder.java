package com.qhp334.drop.fragment.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhp334.drop.R;

import java.util.List;

/**
 * Created by Admin on 2018/3/30.
 */

public class ShareViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ImageView uPicView;

    public TextView uNameView;
    public TextView createTimeView;
    public TextView shareTitleView;

    public View itemView;

    public ShareViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        imageView =itemView.findViewById(R.id.imageView_share);
        uPicView = itemView.findViewById(R.id.uPic_item_share);

        uNameView = itemView.findViewById(R.id.uName_item_share);
        createTimeView = itemView.findViewById(R.id.createTime_item_share);
        shareTitleView = itemView.findViewById(R.id.textView_share_title_share);

    }
}
