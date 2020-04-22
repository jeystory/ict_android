package com.example.ex18_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// 사용자 어댑터를 만들기 위한 클래스
// 반드시 BaseAdapter를 상속받아서 처리해야함
// 생성자는 앞에서 사용한 리스트 어댑터와 같은 구조로 생성
// new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
//                     context,layout,                         ,list
public class UserAdapter extends BaseAdapter{
    Context context;
    int layout;
    ArrayList<VO> list;
    LayoutInflater inflater;
    //생성자에서 받은 변수는 전역변수로 변경
    public UserAdapter(Context context, int layout, ArrayList<VO> list) {
       this.context = context;
       this.layout = layout;
       this.list = list;

        //인플레이터 : user_item 정보를 메모리에 적재시키고 자바에서 해당 메모리에 적재된 정보를 사용할 수 있도록 해야함
        //  LayoutInflator class를 활용
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view ==null){
            view = inflater.inflate(layout,     //레이아웃 형식
                    viewGroup,                     // ViewGroup
                    false);         // 추가여부
        }

        //user_item.xml 내용
        TextView textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.imageView);

        // 해당 위치에서 해당 데이터 설정
        final VO vo = list.get(position);
        textView.setText(vo.getImgName());
        imageView.setImageResource(vo.getResID());

        //이벤트 처리
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, vo.getImgName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // 전체 객수 반환
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




}
