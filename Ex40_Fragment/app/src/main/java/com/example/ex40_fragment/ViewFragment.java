package com.example.ex40_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewFragment extends Fragment {
    ImageView imageView1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_view, container, false);
        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_view, container, false);

        imageView1 = vg.findViewById(R.id.imageView1);


        return vg;
    }

    //이미지 set
    public void setImages(int resId){
        imageView1.setImageResource(resId);
    }
}
