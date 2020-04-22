package com.ict.ex70_cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
                           implements OnPersonitemClickListener{
    ArrayList<VO> items = new ArrayList<>();

    // 리스너 만들기 -1
    OnPersonitemClickListener listener;
    // 리스너 만들기 -2
    public void setOnItemClickListener(OnPersonitemClickListener listener){
        this.listener = listener ;
    }
    //  리스너 만들기-3
    @Override
    public void onItemClick(ViewHolder holder, View view, int postition){
        if(listener != null){
            listener.onItemClick(holder, view, postition);
        }
    }

    // 인플레이트 한다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent,false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VO vo = items.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 정보를 받아서 items에 넣기
     public void addItem(VO vo){
        items.add(vo);
     }
     // 위치값을 받아서 정보 꺼내기
     public VO getItem(int position){
        return items.get(position);
     }
    // 위치값과 정보를 받아서 items에 넣기
     public void setItem(int position, VO item){
        items.set(position, item);
     }

    public ArrayList<VO> getItems() {
        return items;
    }

    public void setItems(ArrayList<VO> items) {
        this.items = items;
    }


    // 뷰를 받아서 처리하는 클래스
    static class ViewHolder extends RecyclerView.ViewHolder{
         TextView textView1 , textView2;

        // 이벤트처리 포함
        public ViewHolder(@NonNull View itemView, final OnPersonitemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

            // 리스너 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
        public void setItem(VO vo){
            textView1.setText(vo.getName());
            textView2.setText(vo.getMobile());
        }

    }
}
