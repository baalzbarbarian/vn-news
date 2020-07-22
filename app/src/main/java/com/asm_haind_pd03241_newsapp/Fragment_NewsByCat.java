package com.asm_haind_pd03241_newsapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.asm_haind_pd03241_newsapp.API.API;
import com.asm_haind_pd03241_newsapp.API.RetrofitClient;
import com.asm_haind_pd03241_newsapp.Adapter.Adapter;
import com.asm_haind_pd03241_newsapp.Model.News;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_NewsByCat extends Fragment {
    List<News> newsList = new ArrayList<>();
    List<News> searchlist = new ArrayList<>();

    RecyclerView rvNews;
    Adapter newsAdapter;
    String catName;
    RetrofitClient retrofitClient = new RetrofitClient();
    EditText edtSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__newsbycat, container, false);

        edtSearch = view.findViewById(R.id.edtSearch);
        rvNews = view.findViewById(R.id.rvNews);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 1);
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(manager);

        if(getArguments() != null){
            catName = getArguments().get("catName").toString();
        }
        getData();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchlist = new ArrayList<>();
                if (s.length() == 0) {
                    searchlist = newsList;
                } else {
                    for (News item : newsList) {
                        if (item.getMatter().toLowerCase().contains(s.toString().toLowerCase())
                                || item.getDate().toLowerCase().contains(s.toString().toLowerCase())) {
                            searchlist.add(item);
                        }
                    }
                }

                newsAdapter = new Adapter(getActivity(), searchlist);
                rvNews.setAdapter(newsAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
    private void getData() {

        API api = retrofitClient.getClient().create(API.class);

        api.getNewsByCat(catName).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                newsList = response.body();

                if(response.isSuccessful()){
                    newsAdapter = new Adapter(getActivity(), newsList);
                    rvNews.setAdapter(newsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

    }
}
