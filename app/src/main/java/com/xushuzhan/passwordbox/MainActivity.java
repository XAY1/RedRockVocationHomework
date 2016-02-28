package com.xushuzhan.passwordbox;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
//00<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    MyDatabaseHelper dbhelper;
    public static List datas = new ArrayList<HashMap<String, Object>>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//1.<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        dbhelper=new MyDatabaseHelper(MainActivity.this,"AccountInformation.db",null,1);
        final SQLiteDatabase db=dbhelper.getWritableDatabase();

        //查询表中所有数据
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        int columnSize = cursor.getColumnCount();

                while (cursor.moveToNext()) {
                    HashMap<String, Object> map = new HashMap<String, Object>();

                        map.put("id", cursor.getString(0));
                        map.put("name", cursor.getString(1));
                        map.put("account", cursor.getString(2));
                        map.put("password", cursor.getString(3));
                        map.put("note", cursor.getString(4));
                        map.put("time", cursor.getString(5));

                   // System.out.println(cursor.getCount() + "数据条目>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
                   // System.out.println(datas.size() + "itemcount>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    datas.add(map);

                }



        //创建RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MyAdapter());

        //为RecyclerView的Item设置监听
            mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener(){

                @Override
                public void onItemClick(View view,HashMap data) {
                    Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                    startActivity(intent);
                    DetailActivity.DtailDatas=data;


                }
            });
        //创建toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //创建右下角的圆形悬浮按钮FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //为FloatingActionButton设置监听
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });

        //创建NavigationDrawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        }catch (Exception e){
            System.out.println(e.getMessage()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }
//为NavigationDrawer设置 按返回键 时，返回到MainActivity
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//自定义toolbar右边的菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


//为NavigationDrawer里面的菜单设置监听
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(MainActivity.this, "此功能尚未开放^-^",
                    Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(MainActivity.this, "此功能尚未开放^-^",
                    Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(MainActivity.this, "此功能尚未开放^-^",
                    Toast.LENGTH_SHORT).show();

        } else if (id == R.id.help) {
            Toast.makeText(MainActivity.this, "此功能尚未开放^-^",
                    Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(MainActivity.this, "此功能尚未开放^-^",
                    Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(MainActivity.this, "此功能尚未开放^-^",
                    Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}





