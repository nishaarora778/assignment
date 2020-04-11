package com.example.asignment1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.asignment1.interfaces.FragmentCommunicationInterface;
import com.example.asignment1.R;
import com.example.asignment1.models.UserModel;
import com.example.asignment1.fragments.DataFragment;
import com.example.asignment1.fragments.UsersFragment;

public class MainActivity extends AppCompatActivity implements FragmentCommunicationInterface {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UsersFragment usersFragment_ = new UsersFragment();
        DataFragment dataFragment_ = new DataFragment();
        fragmentTransaction.add(R.id.frameLayout1, usersFragment_);
        fragmentTransaction.add(R.id.frameLayout2, dataFragment_);
        fragmentTransaction.commit();
    }

    @Override
    public void setDetails(UserModel user) {
        DataFragment dataFragment = new DataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", user.getName());
        bundle.putString("Username", user.getUsername());
        dataFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frameLayout2, dataFragment).commit();
    }
}
