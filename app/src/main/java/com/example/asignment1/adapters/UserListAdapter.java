package com.example.asignment1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.asignment1.activities.MainActivity;
import com.example.asignment1.R;
import com.example.asignment1.models.UserModel;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private Context mContext;
    private List<UserModel> users;

    public UserListAdapter(List<UserModel> users, Context mContext) {
        this.users = users;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_details_holder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_name.setText("Name:-" + users.get(position).getName());
        holder.tv_username.setText("UserName:-" + users.get(position).getUsername());
        holder.tv_email.setText("Email:-" + users.get(position).getEmail());
        holder.tv_address.setText("Address:-"
                + "\n\t Street:-" + users.get(position).getAddress().getStreet()
                + "\n\t Suite:- " + users.get(position).getAddress().getSuite()
                + "\n\t City:- " + users.get(position).getAddress().getCity()
                + "\n\t ZipCode:- " + users.get(position).getAddress().getZipcode());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).setDetails(users.get(position));
            }
        });
    }

    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_username, tv_email, tv_address;
        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_username = itemView.findViewById(R.id.tv_userName);
            tv_email = itemView.findViewById(R.id.tv_Email);
            tv_address = itemView.findViewById(R.id.tv_Address);
        }
    }
}
