package com.github.learn.refreshandload.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.github.learn.base.RefreshMode;
import com.github.learn.refreshandload.R;
import com.smm.lib.recycleview.recyclerviewutils.BaseWrapperRecyclerAdapter;
import com.smm.lib.recycleview.recyclerviewutils.common.ClickableViewHolder;
import com.smm.lib.recycleview.recyclerviewutils.listener.OnRecyclerItemClickListener;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author YanLu
 * @since 15/9/15
 */
public class RefreshSimpleAdapter extends BaseWrapperRecyclerAdapter<RefreshMode, RefreshSimpleAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    public RefreshSimpleAdapter(List<RefreshMode> items) {
        appendToList(items);
    }


    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new RefreshSimpleAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position, List payloads) {
        if(payloads != null && payloads.size() > 0 && vh instanceof RefreshSimpleAdapter.ItemViewHolder){
            for(Object o : payloads){
                if(o != null && o instanceof Integer) {
                    ((RefreshSimpleAdapter.ItemViewHolder) vh).mTvContent.setTextColor((Integer) o);
                }
            }
        } else {
            super.onBindViewHolder(vh, position);
        }
    }

    @Override
    public void onClick(View v, int position) {
        switch (v.getId()){
            case R.id.tv_content:
                notifyItemChanged(position, getRandomColor());
                break;
            default:
                Toast.makeText(v.getContext(), "on click " + position, Toast.LENGTH_SHORT).show();
                //mock click
                remove(getItem(position));
        }
    }


    public class ItemViewHolder extends ClickableViewHolder {
        public TextView mTvContent;

        public ItemViewHolder(View view) {
            super(view);
            mTvContent = (TextView) view.findViewById(R.id.tv_content);
            setOnRecyclerItemClickListener(RefreshSimpleAdapter.this);
            addOnItemViewClickListener();
            addOnViewClickListener(mTvContent);
        }
    }


    public static int getRandomColor() {
        SecureRandom secureRandom = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                secureRandom.nextInt(359), 1, 1
        });
    }
}
