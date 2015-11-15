package com.dream.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dream.R;
import com.dream.activity.BrowserActivity;

/**
 * Created by mengruicheng on 2015/11/14.
 */
public class DreamAdapter extends RecyclerView.Adapter {
    private final static int ITEM_TYPE_PROGRESS = 1;
    private final static int ITEM_TYPE_DREAM = 2;
    public static final int REFLASH_ERROR = 0;
    public static final int REFLASH_ING = 1;
    public static final int REFLASH_SECCUESS = 2;
    public static final int NOT_NEED_REFLASH = -1;
    public int reflashStatu = NOT_NEED_REFLASH;
    private  Context mContext;
    public DreamAdapter(Context context){
            mContext=context;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_PROGRESS) {
            View progress = View.inflate(parent.getContext(), R.layout.reflash_layout, null);
            return new ProgressHolderView(progress);
        } else {

            View pa = View.inflate(parent.getContext(), R.layout.dream_item, null);
            return new DreamHolderView(pa);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (NOT_NEED_REFLASH != reflashStatu && position == getItemCount() - 1) {

            return ITEM_TYPE_PROGRESS;
        }

        return ITEM_TYPE_DREAM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BrowserActivity.class);
                intent.putExtra("url","http://192.168.2.193:9000/detail");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class DreamHolderView extends RecyclerView.ViewHolder {
        public DreamHolderView(View itemView) {
            super(itemView);
        }
    }

    public static class ProgressHolderView extends RecyclerView.ViewHolder {


        public ProgressHolderView(View itemView) {
            super(itemView);
        }
    }
}
