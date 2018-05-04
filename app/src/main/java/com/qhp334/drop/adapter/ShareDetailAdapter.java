package com.qhp334.drop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Admin on 2018/4/3.
 */

public class ShareDetailAdapter extends BaseAdapter {
    public ShareDetailAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    RecyclerView.ViewHolder onCreateVH(ViewGroup parent, LayoutInflater layoutInflater, int viewType) {
        return null;
    }

    @Override
    void onBindVH(RecyclerView.ViewHolder holder, List data, int position) {

    }
}
