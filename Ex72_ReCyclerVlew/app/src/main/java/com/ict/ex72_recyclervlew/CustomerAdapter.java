package com.ict.ex72_recyclervlew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> implements OnCustomerListener{

    ArrayList<VO> list = new ArrayList<>();
    OnCustomerListener listener;

    // 인플레이트
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.items, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VO item = list.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    // MainActivity에서 사용할 메소드
    // 리스트에 정보 추가
    public void addItem(VO vo) {
        list.add(vo);
    }

    // 위치값을 받아서 해당 위치의 존재하는 정보 리턴
    public VO getItem(int position){
        return list.get(position);
    }
    // 리스너
    public  void setOnItemClickListner(OnCustomerListener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1, textView2, textView3;
        ImageView imageView1 ;

        public ViewHolder(@NonNull View itemView, final OnCustomerListener listener) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView1 = itemView.findViewById(R.id.imageView1);

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
            textView2.setText(vo.getPrice());
            textView3.setText(vo.getEvent());
            imageView1.setImageResource(vo.getResId());
        }
    }
}
