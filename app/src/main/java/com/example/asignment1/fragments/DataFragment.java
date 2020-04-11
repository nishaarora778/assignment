package com.example.asignment1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.asignment1.Dialogs.PostsDialog;
import com.example.asignment1.R;
import com.example.asignment1.api.ApiClient;
import com.example.asignment1.api.ApiInterface;

public class DataFragment extends Fragment {
    private Context mContext;
    private int userId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        LinearLayout ll_container = view.findViewById(R.id.ll_container);
        TextView tv_name = view.findViewById(R.id.tv_name_frag2);
        TextView tv_userName = view.findViewById(R.id.tv_userName_frag2);
        Button btn_GetPosts = view.findViewById(R.id.btn_getPost);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt("userId",1);
            String name = bundle.getString("Name");
            String username = bundle.getString("Username");
            tv_name.setText(name);
            tv_userName.setText(username);
            ll_container.setVisibility(View.VISIBLE);
        }
        else
            ll_container.setVisibility(View.GONE);
        btn_GetPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostsDialog dialog = new PostsDialog(mContext, userId);
                dialog.show();
            }
        });
        return view;
    }
}
