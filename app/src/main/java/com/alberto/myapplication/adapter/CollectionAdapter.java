package com.alberto.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alberto.myapplication.R;
import com.alberto.myapplication.databinding.CollectionsRecyclerViewItemBinding;
import com.alberto.myapplication.model.CollectionModel;
import com.alberto.myapplication.model.ProductInfoModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {

    private List<CollectionModel> collectionList;

    public CollectionAdapter(List<CollectionModel> collectionList) {
        this.collectionList = collectionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CollectionsRecyclerViewItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.collections_recycler_view_item, parent, false);

        return new MyViewHolder(binding);
    }

    public void setCollectionList(List<CollectionModel> collectionList) {
        this.collectionList = collectionList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HashMap<String, ProductInfoModel> productValues = collectionList.get(position).getProductValues();
        List<ProductInfoModel> productInfoList = new ArrayList<ProductInfoModel>();
        productInfoList.addAll(productValues.values());

        Glide.with(holder.binding.collectionImage1.getContext())
                .load(productInfoList.get(0).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.binding.collectionImage1);

        Glide.with(holder.binding.collectionImage2.getContext())
                .load(productInfoList.get(1).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.binding.collectionImage2);

        Glide.with(holder.binding.collectionImage3.getContext())
                .load(productInfoList.get(2).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.binding.collectionImage3);

        holder.binding.collectionCount.setText(String.valueOf(productInfoList.size()));
        holder.binding.collectionName.setText(collectionList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CollectionsRecyclerViewItemBinding binding;

        public MyViewHolder(@NonNull CollectionsRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
