package com.example.asignment1.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asignment1.R;
import com.example.asignment1.adapters.PostListAdapter;
import com.example.asignment1.api.ApiClient;
import com.example.asignment1.api.ApiInterface;
import com.example.asignment1.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsDialog extends Dialog {
    private RecyclerView rvPosts;
    private ApiInterface apiInterface;
    private Context mContext;
    private int userId;

    public PostsDialog(@NonNull Context context, int userId) {
        super(context);
        mContext = context;
        this.userId = userId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_dialog_layout);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        rvPosts = findViewById(R.id.recyclerView_post);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(mContext);
        rvPosts.setLayoutManager(layoutManager);
//        rvPosts.setHasFixedSize(true);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        postApiHit();
    }

    private void postApiHit(){
        Call<List<PostModel>> call = apiInterface.getPost(userId);
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Posts Fetched", Toast.LENGTH_SHORT).show();
                }
                List<PostModel> postModels = response.body();
                if (postModels!= null){
                    PostListAdapter postListAdapter = new PostListAdapter(mContext, postModels);
                    rvPosts.setAdapter(postListAdapter);
                }
                else
                    Toast.makeText(getContext(), "No Posts found", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Post Fetching Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
