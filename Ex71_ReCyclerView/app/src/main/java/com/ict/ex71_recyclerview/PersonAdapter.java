package com.ict.ex71_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
                            implements  OnPersonItemClicListener{

    ArrayList<VO> list = new ArrayList<>();

    OnPersonItemClicListener listener;

    public void setOnItemClickListener(OnPersonItemClicListener listener){
        this.listener = listener ;
    }
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    // 인플레이트
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item,parent,false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // 나머지 처리해야할 메소드
    // 객체을  받아서 리스트에 넣어주기
    public void addItem(VO vo){
        list.add(vo);
    }
    // 위치값을 받으면 해당 객체를 출력하기
    public VO getItem(int position){
        return list.get(position);
    }

    public void setItem(int position,VO vo){
        list.set(position,vo);
    }
    public ArrayList<VO> getList(){
        return list;
    }
    public void setList(ArrayList<VO> vo){
        this.list = vo;
    }




    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView t_name;
        TextView t_birth;
        TextView t_phone;
        ImageView imageView1;

        public ViewHolder(View itemView, final OnPersonItemClicListener listener) {
            super(itemView);

            t_name = itemView.findViewById(R.id.t_name);
            t_birth = itemView.findViewById(R.id.t_birth);
            t_phone = itemView.findViewById(R.id.t_phone);
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
        // 각 각의 정보를 받아서 넣주기
        public void setItem(VO vo){
            t_name.setText(vo.getName());
            t_birth.setText(vo.getBirth());
            t_phone.setText(vo.getPhone());
            imageView1.setImageResource(vo.getResld());
        }
    }

}
