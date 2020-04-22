package com.example.ex57_mydrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        // values/styles  <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar" 로 변경해야된다.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,R.string.close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //이벤트 처리
        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               if(menuItem.getItemId() == R.id.menu1){
                   //toolbar.setTitle("Camera");
                   //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                   Intent intent = new Intent();
                   intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                   if (intent.resolveActivity(getPackageManager()) != null) {
                       startActivity(intent);
                   }
               }else if(menuItem.getItemId() == R.id.menu2) {
                   //toolbar.setTitle("Gallery");
                   //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                   Intent intent = new Intent();
                   intent.setAction(Intent.ACTION_VIEW);
                   intent.setType("image/*");
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   if (intent.resolveActivity(getPackageManager()) != null) {
                       startActivity(intent);
                   }
               }else if(menuItem.getItemId() == R.id.menu3) {
                   //toolbar.setTitle("SlideShow");
                   //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                   Intent intent = new Intent();
                   intent.setAction(Intent.ACTION_VIEW);
                   intent.setType(CallLog.Calls.CONTENT_TYPE);
                   if (intent.resolveActivity(getPackageManager()) != null) {
                       startActivity(intent);
                   }
               }

               DrawerLayout drawer = findViewById(R.id.drawer_layout);
               drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
}
