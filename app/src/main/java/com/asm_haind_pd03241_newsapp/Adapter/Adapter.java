package com.asm_haind_pd03241_newsapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.asm_haind_pd03241_newsapp.Detail;
import com.asm_haind_pd03241_newsapp.Model.News;
import com.asm_haind_pd03241_newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerViewHolder> {
    Activity context;
    List<News> data;

    public Adapter(Activity context, List<News> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Adapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new Adapter.RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final Adapter.RecyclerViewHolder holder, final int position) {
        News news = this.data.get(position);

        try {
            Picasso.with(context)
                    .load("http://192.168.43.188:3000/uploads/" + news.getImage())
                    .into(holder.image);
        } catch (Exception e) {

        }

        holder.txtNewsMatter.setText(news.getMatter());
        holder.txtDate.setText(news.getDate());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("id", data.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtNewsMatter;
        TextView txtDate;
        CardView item;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.newsImage);
            txtNewsMatter = itemView.findViewById(R.id.txt_newsMatter);
            txtDate = itemView.findViewById(R.id.txt_newsDate);
            item = itemView.findViewById(R.id.item);
        }
    }
}
