package com.asm_haind_pd03241_newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.asm_haind_pd03241_newsapp.API.API;
import com.asm_haind_pd03241_newsapp.API.RetrofitClient;
import com.asm_haind_pd03241_newsapp.Model.Category;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    RetrofitClient retrofitClient = new RetrofitClient();
    API api;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.viewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        getCategoriesList();

        tabLayout.setupWithViewPager(viewPager);

    }

    public void getCategoriesList(){
        api = retrofitClient.getClient().create(API.class);
        Call<List<Category>> call = api.getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    for (int i=0;i<response.body().size();i++){
                        Fragment_NewsByCat pageFragment=  new Fragment_NewsByCat();
                        Bundle bundle = new Bundle();
                        adapter.addFragment(pageFragment, response.body().get(i).getCatName()+"");
                        bundle.putString("catName", response.body().get(i).getCatName()+"" );
                        viewPager.setAdapter(adapter);
                        pageFragment.setArguments(bundle);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
            }
        });
    }


}
