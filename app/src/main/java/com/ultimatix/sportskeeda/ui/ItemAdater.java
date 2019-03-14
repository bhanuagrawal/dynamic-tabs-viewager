package com.ultimatix.sportskeeda.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ultimatix.sportskeeda.R;
import com.ultimatix.sportskeeda.data.entities.Item;
import com.ultimatix.sportskeeda.data.entities.Tab;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdater<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int ITEMS = 0;
    public static final int TABS = 1;

    private final int itemVIewType;
    private ArrayList<T> mData;
    private ItemAdaterListner itemAdaterListner;
    private Context context;

    public ItemAdater(Context context, ItemAdaterListner itemAdaterListner, int viewType) {
        this.mData = new ArrayList<>();
        this.itemAdaterListner = itemAdaterListner;
        this.context = context;
        this.itemVIewType = viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (itemVIewType){
            case ITEMS:
                ItemViewHolder itemViewHolder = new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
                return itemViewHolder;
            case TABS:
                CheckBoxViewHolder checkBoxViewHolder = new CheckBoxViewHolder(LayoutInflater.from(context).inflate(R.layout.tab, parent, false));
                return checkBoxViewHolder;
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case ITEMS:
                ((ItemViewHolder)holder).onBind((Item) mData.get(position));
                break;
            case TABS:
                ((CheckBoxViewHolder)holder).onBind((Tab) mData.get(position));
                break;
        }



    }


    @Override
    public int getItemViewType(int position) {
        return itemVIewType;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void onDataChange(ArrayList<T> data) {
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv1)
        TextView tv1;

        @BindView(R.id.tv2)
        TextView tv2;

        @BindView(R.id.tv3)
        TextView tv3;


        @BindView(R.id.imageView2)
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void onBind(Item item) {
            tv1.setText(item.getTitle());
            tv2.setText("Rank: " + item.getRank().longValue());
            tv3.setText("Author: " + item.getAuthor().getName());
            Glide.with(context)
                    .load(item.getThumbnail())
                    .into(imageView);
        }


    }

    class CheckBoxViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkBox)
        CheckBox checkBox;

        public CheckBoxViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void onBind(final Tab tab) {
            checkBox.setText(tab.getType());
            if(tab.isVisible() && !checkBox.isChecked()){
                checkBox.setChecked(true);
            }
            else if(!tab.isVisible() && checkBox.isChecked()){
                checkBox.setChecked(false);
            }


            checkBox.setChecked(tab.isVisible()?true:false);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    tab.setVisible(checkBox.isChecked());
                    itemAdaterListner.updateTabsVisibility(tab);
                }
            });
        }


    }



    public interface ItemAdaterListner{
        void updateTabsVisibility(Tab tab);
    }
}
