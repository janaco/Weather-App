package com.nandy.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yana on 21.10.17.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>{

    private final List<String> items;
    private OnListItemClickListener<String> onListItemClickListener;

    public SearchResultsAdapter(List<String> items) {
        this.items = items;
    }

    public void setItems(List<String> items) {
        this.items.clear();
        this.items.addAll(items);

        notifyDataSetChanged();
    }

    public void setOnListItemClickListener(OnListItemClickListener<String> onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String item = items.get(position);
        holder.setText(item);
        holder.view.setOnClickListener(view -> onListItemClickListener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text)
        TextView textView;

        View view;

        ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        void setText(String text){
            textView.setText(text);
        }
    }
}
