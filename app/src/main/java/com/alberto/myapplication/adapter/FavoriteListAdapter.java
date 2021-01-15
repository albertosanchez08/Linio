package com.alberto.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alberto.myapplication.R;
import com.alberto.myapplication.constants.ProductInfoConstants;
import com.alberto.myapplication.databinding.FavoritesRecyclerViewItemBinding;
import com.alberto.myapplication.model.ProductInfoModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder> {

    private List<ProductInfoModel> productInfoList;

    public FavoriteListAdapter(List<ProductInfoModel> collectionList) {
        this.productInfoList = collectionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FavoritesRecyclerViewItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.favorites_recycler_view_item, parent, false);
        return new MyViewHolder(binding);
    }

    public void setProductInfoList(List<ProductInfoModel> productInfoList) {
        this.productInfoList = productInfoList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(holder.binding.productImageView.getContext())
                .load(productInfoList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.binding.productImageView);

        switch(productInfoList.get(position).getLinioPlusLevel()) {
            case ProductInfoConstants.LINIO_PLUS_LEVEL0 :
                break;
            case ProductInfoConstants.LINIO_PLUS_LEVEL1 :
                holder.binding.plusImageView.setVisibility(View.VISIBLE);
                break;
            case ProductInfoConstants.LINIO_PLUS_LEVEL2 :
                holder.binding.plus48ImageView.setVisibility(View.VISIBLE);
                break;
        }

        switch(productInfoList.get(position).getConditionType()) {
            case ProductInfoConstants.NEW_CONDITION_TYPE :
                holder.binding.newProductImageView.setVisibility(View.VISIBLE);
                break;
            case ProductInfoConstants.REFURBISHED_CONDITION_TYPE :
                holder.binding.refurbishedImageView.setVisibility(View.VISIBLE);
                break;
        }

        if(productInfoList.get(position).isFreeShipping()) {
            holder.binding.freeShippingImageView.setVisibility(View.VISIBLE);
        }

        if(productInfoList.get(position).isImported()) {
            holder.binding.airplaneImageView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return productInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private FavoritesRecyclerViewItemBinding binding;

        public MyViewHolder(@NonNull FavoritesRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
