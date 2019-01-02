package com.qiniu.csvc.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mDatas;
    protected Context mContext;
    protected LayoutInflater inflater;
    AdapterView.OnItemClickListener mItemClickListener;

    public BaseRecyclerAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final T item = getItem(position);
        bindItemData(holder, item, position);
        setupOnItemClick(holder, position);
    }

    protected abstract void bindItemData(VH viewHolder, T data, int position);

    protected void setupOnItemClick(final VH viewHolder, final int position) {
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(null, viewHolder.itemView, position, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public T getItem(int position) {
        position = Math.max(0, position);
        return mDatas.get(position);
    }

    public List<T> getDataSource() {
        return mDatas;
    }

    public void setData(List<T> lists) {
        this.mDatas = lists;
        notifyDataSetChanged();
    }

    public void setDataNoUpdate(List<T> lists) {
        this.mDatas = lists;
    }

    public void addData(List<T> newItems) {
        if (newItems != null) {
            mDatas.addAll(newItems);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemChanged(position);
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
