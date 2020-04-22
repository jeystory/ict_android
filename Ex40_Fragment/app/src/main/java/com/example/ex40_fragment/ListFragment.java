package com.example.ex40_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class ListFragment extends Fragment {

    // MainActivity에서 실행할 CallBack 함수 만들기
    public ImageSelectionCallBack callBack;


    public static interface ImageSelectionCallBack{
        //추상메소드
        public void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ImageSelectionCallBack){
            callBack = (ImageSelectionCallBack)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_list, container, false);

        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);

        Button button1 = vg.findViewById(R.id.button1);
        Button button2 = vg.findViewById(R.id.button2);
        Button button3 = vg.findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack != null) callBack.onImageSelected(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.onImageSelected(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.onImageSelected(2);
            }
        });

        return vg;
    }
}
