package com.alberto.myapplication.data.network;

import com.alberto.myapplication.model.CollectionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("wishlist.json")
    Call<List<CollectionModel>> getCollectionList();
}
