package com.asm_haind_pd03241_newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.asm_haind_pd03241_newsapp.API.API;
import com.asm_haind_pd03241_newsapp.API.RetrofitClient;
import com.asm_haind_pd03241_newsapp.Adapter.Adapter;
import com.asm_haind_pd03241_newsapp.Model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    TextView txtMatter, txtAuthor, txtDate, txtContent;
    ImageView image;
    RetrofitClient retrofitClient = new RetrofitClient();
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String newsId = intent.getStringExtra("id");

        initView();

        getData(newsId);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tin tức chi tiết");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        txtAuthor = findViewById(R.id.txt_author);
        txtContent = findViewById(R.id.txt_content);
        txtDate = findViewById(R.id.txt_date);
        txtMatter = findViewById(R.id.txt_matter);
        image = findViewById(R.id.img_image);

    }

    private void getData(String newsId) {

        api = retrofitClient.getClient().create(API.class);

        api.getNewsById(newsId).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> newsList = response.body();
                if(response.isSuccessful()){
                    for (News item : newsList) {
                        Picasso.with(Detail.this).load(
                                    "http://192.168.43.188:3000/uploads/" + item.getImage()).into(image);
                        txtMatter.setText(item.getMatter());
                        txtAuthor.setText(item.getAuthor());
                        txtDate.setText(item.getDate());
                        txtContent.setText(item.getContent());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

    }

}
