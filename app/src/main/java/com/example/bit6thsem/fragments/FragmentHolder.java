package com.example.bit6thsem.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bit6thsem.R;

public class FragmentHolder extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);
        addFragment();
    }
    private void addFragment(){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();

        FragmentOne fragmentOne = new FragmentOne();

        //we use bundle to pass data in Fragment(s)
        Bundle bundle = new Bundle();
        bundle.putString("name", "peter");


        fragmentOne.setArguments(bundle);

        transaction.add(R.id.flFragmentContainerRoot, new FragmentOne());
        transaction.commit();
    }

    public void replaceFragment(){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragmentContainerRoot, new Fragment2());
        transaction.commit();
    }
}
