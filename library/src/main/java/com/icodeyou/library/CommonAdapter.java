package com.icodeyou.library;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huan on 15/12/22.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> mList;

    private Context mContext;

    public CommonAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    public CommonAdapter(Context context, List<T> list) {
        init(context, list);
    }

    private void init(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    public List<T> getmList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }

    public void clear() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (list != null) {
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }


    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflate(getContentView());
        }
        onInitView(convertView, position);
        return convertView;
    }

    private View inflate(int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutResId, null);
        return view;
    }

    public abstract int getContentView();

    public abstract void onInitView(View view, int position);

    @SuppressWarnings("unchecked")
    protected <E extends View> E get(View view, int id){
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (E) childView;
    }
}
