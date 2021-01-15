package com.alberto.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alberto.myapplication.R;
import com.alberto.myapplication.adapter.CollectionAdapter;
import com.alberto.myapplication.adapter.FavoriteListAdapter;
import com.alberto.myapplication.databinding.ActivityMainBinding;
import com.alberto.myapplication.model.CollectionModel;
import com.alberto.myapplication.model.ProductInfoModel;
import com.alberto.myapplication.viewmodel.FavoritesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FavoritesViewModel viewModel;
    private CollectionAdapter collectionAdapter;
    private FavoriteListAdapter favoriteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        viewModel.makeApiCall();
        loadCollectionInfo(binding);
        loadFavoritesInfo(binding);
    }

    private void loadFavoritesInfo(ActivityMainBinding binding) {
        viewModel.getFavoritesList().observe(this, new Observer<List<ProductInfoModel>>() {
            @Override
            public void onChanged(List<ProductInfoModel> productInfoModels) {
                favoriteListAdapter = new FavoriteListAdapter(productInfoModels);
                LinearLayoutManager linearLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                binding.favoritesRecyclerView.setLayoutManager(linearLayoutManager);
                binding.favoritesRecyclerView.setAdapter(favoriteListAdapter);
                favoriteListAdapter.setProductInfoList(productInfoModels);
                binding.favoritesCount.setText(getString(R.string.all_favorites_label,productInfoModels.size()));
            }
        });
    }

    private void loadCollectionInfo(ActivityMainBinding binding) {
        viewModel.getCollectionListObservable().observe(this, new Observer<List<CollectionModel>>() {
            @Override
            public void onChanged(List<CollectionModel> collectionModels) {
                collectionAdapter = new CollectionAdapter(collectionModels);
                LinearLayoutManager collectionsLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                binding.collectionRecyclerView.setLayoutManager(collectionsLayoutManager);
                binding.collectionRecyclerView.setAdapter(collectionAdapter);
                collectionAdapter.setCollectionList(collectionModels);
            }
        });
    }
}