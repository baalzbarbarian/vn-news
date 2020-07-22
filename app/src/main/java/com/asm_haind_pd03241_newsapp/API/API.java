package com.asm_haind_pd03241_newsapp.API;

import com.asm_haind_pd03241_newsapp.Model.Category;
import com.asm_haind_pd03241_newsapp.Model.News;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("/api/getNewsById/{id}")
    Call<List<News>> getNewsById(@Path("id") String id);

    @GET("/api/category")
    Call<List<Category>> getCategory();

    @GET("/api/newsByCat/{NewsCat}")
    Call<List<News>> getNewsByCat(@Path("NewsCat") String NewsCat);

}

