package com.example.asignment1.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.asignment1.api.ApiClient;
import com.example.asignment1.api.ApiInterface;
import com.example.asignment1.R;
import com.example.asignment1.adapters.UserListAdapter;
import com.example.asignment1.models.UserModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {
    private ApiInterface mApiInterface;
    private UserListAdapter myAdapter;
    private RecyclerView recyclerView;
    private TextView tv_msg;
    private Context mContext;
    private Button btn_Retry;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_users);
        btn_Retry = view.findViewById(R.id.Retry_btn);
        tv_msg = view.findViewById(R.id.tv_msg);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (!checkInternetConnection()) {
            setNoInternet();
        }
        setNoUsers();
        return view;
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null &&
                        activeNetworkInfo.isConnected() &&
                        (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI || activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) ){
                    setNoUsers();
                    return true;
                }
            } else if (Build.VERSION.SDK_INT > 23) {
                final Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    final NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    if (networkCapabilities != null &&
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        setNoUsers();
                        return true;
                    }
                }
            } else {
                Toast.makeText(mContext, R.string.no_internet, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    private void userApiHit() {
        if (!checkInternetConnection()) {
            setNoInternet();
            return;
        }
        Call<List<UserModel>> call = mApiInterface.getUser();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    List<UserModel> users = response.body();
                    if (users != null) {
                        myAdapter = new UserListAdapter(users, mContext);
                        recyclerView.setAdapter(myAdapter);
                        tv_msg.setVisibility(View.GONE);
                        btn_Retry.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(mContext, R.string.user_no_input, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(mContext, R.string.no_response, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setNoUsers() {
        tv_msg.setText(R.string.no_users);
        btn_Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userApiHit();
            }
        });
    }

    private void setNoInternet() {
        tv_msg.setText(R.string.no_internet);
        btn_Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetConnection();
            }
        });
    }
}
