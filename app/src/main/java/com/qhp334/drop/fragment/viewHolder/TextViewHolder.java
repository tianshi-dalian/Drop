package com.qhp334.drop.fragment.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhp334.drop.R;

/**
 * Created by Admin on 2018/3/30.
 */

public class TextViewHolder extends RecyclerView.ViewHolder {
    public ImageView uPicView;
    public TextView uNameView;
    public TextView tCreateTimeView;
    public TextView tDetailView;

    public View itemView;

    public TextViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        uPicView = itemView.findViewById(R.id.uPic);
        uNameView = itemView.findViewById(R.id.uName);
        tDetailView = itemView.findViewById(R.id.textView_textDetail);
        tCreateTimeView = itemView.findViewById(R.id.createTime);
    }
}
