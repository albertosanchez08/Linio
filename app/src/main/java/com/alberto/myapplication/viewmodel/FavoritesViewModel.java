package com.alberto.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alberto.myapplication.data.network.ApiService;
import com.alberto.myapplication.data.network.RetroInstance;
import com.alberto.myapplication.model.CollectionModel;
import com.alberto.myapplication.model.ProductInfoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesViewModel extends ViewModel {

    private MutableLiveData<List<CollectionModel>> collectionList;

    private MutableLiveData<List<ProductInfoModel>> favoritesList;

    public FavoritesViewModel() {
        collectionList = new MutableLiveData<>();
        favoritesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CollectionModel>> getCollectionListObservable() {
        return collectionList;
    }

    public void makeApiCall() {
        ApiService apiService = RetroInstance.getRetroClient().create(ApiService.class);
        Call<List<CollectionModel>> call = apiService.getCollectionList();
        call.enqueue(new Callback<List<CollectionModel>>() {
            @Override
            public void onResponse(Call<List<CollectionModel>> call, Response<List<CollectionModel>> response) {
                collectionList.postValue(response.body());
                getAllFavoritesInfo(response.body());
            }

            @Override
            public void onFailure(Call<List<CollectionModel>> call, Throwable t) {
                collectionList.postValue(null);
            }
        });
    }

    private void getAllFavoritesInfo(List<CollectionModel> collectionList) {
        List<ProductInfoModel> productInfoList = new ArrayList<ProductInfoModel>();
        for(CollectionModel collectionModel : collectionList) {
            HashMap<String, ProductInfoModel> productValues = collectionModel.getProductValues();
            productInfoList.addAll(productValues.values());

        }
        favoritesList.postValue(productInfoList);
    }

    public MutableLiveData<List<ProductInfoModel>> getFavoritesList() {
        return favoritesList;
    }
}
