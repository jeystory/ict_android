package com.example.ex41_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_View  extends Fragment {
    TextView name, addr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_view, container, false);
        name = vg.findViewById(R.id.name);
        addr = vg.findViewById(R.id.addr);
        return vg;
    }

    //Fragment_List에서 이벤트가 발생하면 실행할 메소드
    public void user_Change(String name, String addr){
        this.name.setText(name);
        this.addr.setText(addr);
    }
}
