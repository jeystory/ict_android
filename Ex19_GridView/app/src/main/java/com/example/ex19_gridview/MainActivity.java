package com.example.ex19_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    MyGridAdapter adapter;
    Integer[] imgID ={R.drawable.pic1, R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,
            R.drawable.pic5,R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};
    String[] imgName ={"그림1","그림2","그림3","그림4", "그림5","그림6", "그림7","그림8","그림9" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        adapter = new MyGridAdapter(this);
        gridView.setAdapter(adapter);
    }

    //내부 클래스를 활용한 어댑터 : 생성자에서 레이아웃, 리스트(배열)
    class MyGridAdapter extends BaseAdapter{
        Context context;
        /*
        Integer[] imgID ={R.drawable.pic1, R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,
                R.drawable.pic5,R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};
        String[] imgName ={"그림1","그림2","그림3","그림4", "그림5","그림6", "그림7","그림8","그림9" };
        */
        public MyGridAdapter() {
        }

        public MyGridAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            return imgID.length;
        }

        @Override
        public Object getItem(int position) {
            return imgID[position];
        }

        @Override
        public long getItemId(int position) {
           return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            //리턴할 이미지뷰를 생성
            ImageView imageView = new ImageView(context);
            //이미지 뷰 크기 지정
            imageView.setLayoutParams(new GridView.LayoutParams(400, 350));
            //이미지 여백
            imageView.setPadding(5,10,5,10);

            //이미지 배치
            imageView.setImageResource(imgID[position]);

            //이벤트 처리
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, imgName[position], Toast.LENGTH_SHORT).show();
                }
            });

            return imageView;
        }
    }
}
