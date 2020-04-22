package com.example.ex40_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallBack{
    ListFragment listFragment;
    ViewFragment viewFragment;
    int[] images ={R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ListFragment)manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment)manager.findFragmentById(R.id.viewFragment);
    }

   public void  onImageSelected(int position){
        viewFragment.setImages(images[position]);
    }
}
