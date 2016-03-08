package com.xushuzhan.passwordbox;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mr.Xu on 2016/1/30.
 */
public class EditActivity extends AppCompatActivity {
    Button okButton;
    private EditText edit_1;
    private EditText edit_2;
    private EditText edit_3;
    private EditText edit_4;
    MyDatabaseHelper dbhelper;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        okButton=(Button)findViewById(R.id.edit_delete);
        edit_1= (EditText) findViewById(R.id.name);
        edit_2=(EditText)findViewById(R.id.account);
        edit_3=(EditText)findViewById(R.id.password);
        edit_4=(EditText)findViewById(R.id.note);



        dbhelper=new MyDatabaseHelper(EditActivity.this,"AccountInformation.db",null,1);

        //getWritableDatabase()会返回一个SQLiteDatabase对象，可以借助他对数据库进行增删改查
        final SQLiteDatabase db=dbhelper.getWritableDatabase();

        //向SQLite添加数据
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日 HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());//获取当前时间
        final String   NowTime   =   formatter.format(curDate);




        //为okButton设置监听
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout ti_1 = (TextInputLayout) findViewById(R.id.p_name);
                TextInputLayout ti_2 = (TextInputLayout) findViewById(R.id.p_account);
                TextInputLayout ti_3 = (TextInputLayout) findViewById(R.id.p_password);
                TextInputLayout ti_4 = (TextInputLayout) findViewById(R.id.p_note);
                    if (edit_1.getText().toString().equals("")) {
                        ti_1.setError("名称不能为空");
                    }else if(edit_2.getText().toString().equals("")){
                        ti_2.setError("用户名不能为空");
                    } else if(edit_3.getText().toString().equals("")){
                        ti_3.setError("密码不能为空");
                    } else{
                    ContentValues values = new ContentValues();
                    //组装数据
                    values.put("name", edit_1.getText().toString());
                    values.put("account", edit_2.getText().toString());
                    values.put("password", edit_3.getText().toString());
                    values.put("note", edit_4.getText().toString());
                    values.put("time", NowTime);
                    //往SQLite加入数据
                    db.insert("user", null, values);
                    MyAdapter mAdapter = new MyAdapter();

                    Toast.makeText(EditActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                    MainActivity.datas.removeAll(MainActivity.datas);
                    finish();
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
    //监听Back键按下事件
    @Override
    public void onBackPressed() {
        finish();
        Intent intent=new Intent(EditActivity.this,MainActivity.class);
        startActivity(intent);

    }
}

//创建或打开一个数据库

