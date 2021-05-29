package com.taolibrary.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件作者：余涛
 * 功能描述：RecycleView适配器的基类
 * 创建时间：2019/11/5 15:59
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    protected List<T> mList;
    protected Context mContext;

    public BaseRecyclerViewAdapter() {
        mList = new ArrayList<>();
    }

    public BaseRecyclerViewAdapter(List<T> list) {
        mList = list;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view;
        view = setViewBinding(parent);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(getItemLayoutId(viewType), parent, false);
        }
        setContentView(view);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter.BaseViewHolder holder, int position) {
        BaseViewHolder viewHolder= (BaseViewHolder) holder;
        onBindDataToView(viewHolder, mList.get(position), position);
        if (mOnLayoutListener != null) {
            mOnLayoutListener.onLayout(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    //获取item的view，可以做一些适配操作
    public void setContentView(View view) {

    }

    public View setViewBinding(ViewGroup parent){
        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public void setDataList(List<T>  list) {
        mList = list;
    }

    public List<T> getDataList() {
        return mList;
    }


    public void updateData(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        mList = list;
        notifyDataSetChanged();
    }

    public void add(T bean) {
        mList.add(bean);
        notifyDataSetChanged();
    }

    public void addAll(List<T> beans) {
        mList.addAll(beans);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;
        public final View itemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
            this.itemView = itemView;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null && mList.size() != 0) {
                        mOnItemClickListener.onItemClick(getAdapterPosition());
                    }
                    onItemClick(getAdapterPosition());
                }
            });
            this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null && mList.size() != 0) {
                        mOnItemLongClickListener.onLongClick(getAdapterPosition());
                    }
                    return false;
                }
            });
            setTextChangedListener(this, itemView);
            setRatingBarListener(this, itemView);
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }

        public void setImage(int viewId, Object params) {
            ImageView iv = getView(viewId);
            if (params instanceof String) {
                //自己写加载图片的逻辑
            } else if (params instanceof Integer) {
                iv.setImageResource((Integer) params);
            } else if (params instanceof Bitmap) {
                iv.setImageBitmap((Bitmap) params);
            } else if (params instanceof Drawable) {
                iv.setImageDrawable((Drawable) params);
            } else {
                try {
                    throw new Exception("params is wrong!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        public void setClickListener(int viewId) {
            View v = getView(viewId);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSingleViewClick(v, getAdapterPosition());
                }
            });

        }


        public int getCurrentPosition() {
            return getAdapterPosition();
        }
    }

    /**
     * 绑定布局id
     *
     * @param viewType
     * @return
     */
    protected abstract int getItemLayoutId(int viewType);

    /**
     * 绑定数据
     *
     * @param holder
     * @param t
     */
    protected abstract void onBindDataToView(BaseViewHolder holder, T t, int position);


    /**
     * ItemView里的某个子控件的单击事件(如果需要，重写此方法就行)
     * 只有在{@link #onBindDataToView}注册了{@link BaseViewHolder#setClickListener}才有效果
     *
     * @param position
     */
    protected void onSingleViewClick(View view, int position) {

    }

    /**
     * ItemView的单击事件(如果需要，重写此方法就行)
     *
     * @param position
     */
    protected void onItemClick(int position) {

    }

    /**
     * 为edittext添加文本监听(如果需要，重写此方法就行)
     *
     * @param holder
     * @param itemView
     */
    protected void setTextChangedListener(BaseViewHolder holder, View itemView) {

    }

    protected void setRatingBarListener(BaseViewHolder holder, View itemView) {

    }

    /**
     * 点击布局监听
     */
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 长按布局监听
     */
    private OnItemLongClickListener mOnItemLongClickListener;
    public interface OnItemLongClickListener {
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 布局控件内监听
     */
    private OnLayoutListener mOnLayoutListener;
    public interface OnLayoutListener {
        void onLayout(BaseRecyclerViewAdapter.BaseViewHolder holder, int position);
    }
    public void setOnLayoutListener(OnLayoutListener onLayoutListener) {
        mOnLayoutListener = onLayoutListener;
    }
}
