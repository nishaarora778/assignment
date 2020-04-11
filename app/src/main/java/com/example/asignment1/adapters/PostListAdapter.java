package com.example.asignment1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asignment1.R;
import com.example.asignment1.models.PostModel;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder1> {
    private Context mContext;
    private List<PostModel> posts;
    public PostListAdapter(Context mContext, List<PostModel> posts) {
        this.mContext = mContext;
        this.posts = posts;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.post_details_holder, parent,false);
       return (new MyViewHolder1(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        holder.tv_title.setText(posts.get(position).getTitle());
        holder.tv_body.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView tv_title,tv_body;
        MyViewHolder1(@NonNull View itemView) {
            super(itemView);
          tv_body = itemView.findViewById(R.id.tv_body);
          tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}

