package com.example.ex41_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_List extends Fragment {
    ListView list;
    String[] users = {"홍길동", "임꺽정", "장길산", "일지매"};
    String[] addr = {"충청도", "함경도", "전라도", "경상도"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);

        // 이벤트 처리
        list = vg.findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,users);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment_View fragment_view =
                        (Fragment_View)getFragmentManager().findFragmentById(R.id.fragment_view);
                fragment_view.user_Change("이름 : "+ users[position] , "주소 : "+addr[position] );
            }
        });

        return vg;
    }
}
