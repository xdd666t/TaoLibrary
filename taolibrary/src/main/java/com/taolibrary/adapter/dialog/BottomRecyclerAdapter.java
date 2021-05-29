package com.taolibrary.adapter.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.taolibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/6/18 9:44
 */
public class BottomRecyclerAdapter extends RecyclerView.Adapter<BottomRecyclerAdapter.MyViewHolder> {
    private List<String> mList;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public BottomRecyclerAdapter(List<String> list) {
        if (list == null) {
            mList = new ArrayList<>();
        } else {
            mList = list;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_bottom_list, viewGroup, false);
        BottomRecyclerAdapter.MyViewHolder myViewHolder = new BottomRecyclerAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        myViewHolder.mText.setText(mList.get(position));

        //点击事件
        if (mOnClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onClick(position);
                }
            });
        }

        //长按事件
        if (mOnLongClickListener != null) {
            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnLongClickListener.OnLongClick(position);
                    return true;
                }
            });
        }
    }

    public void updateData(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }


    /**
     * 增加数据
     * @param str
     */
    public void addData(String str) {
        mList.add(str);
        notifyItemInserted(mList.size());
    }

    public void addData(int position, String str) {
        mList.add(position, str);
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     * 当list列表数据为1时,禁止删除
     * 返回值说明 0:说明移除所有数据,此时可以关掉dialog了   1:说明还有数据
     * @param position
     */
    public int removeData(int position) {
        if (mList.size() == 1){
            mList = null;
            return 0;
        }

        mList.remove(position);
        notifyItemRemoved(position);

        return 1;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mText;
        MyViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.item_text);
        }
    }


    /**
     * 点击接口
     */
    public interface OnClickListener{
        void onClick(int position);
    }

    /**
     * 长按接口
     */
    public interface OnLongClickListener {
        void OnLongClick(int position);
    }
}
