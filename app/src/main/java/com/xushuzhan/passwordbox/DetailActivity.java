package com.xushuzhan.passwordbox;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Mr.Xu on 2016/2/24.
 */
public class DetailActivity extends AppCompatActivity {
    public static HashMap<String, Object> DtailDatas = new HashMap<String, Object>();

    String idToDel=DtailDatas.get("id").toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(DtailDatas.get("name").toString());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

         MyDatabaseHelper dbHelper=new MyDatabaseHelper(DetailActivity.this,"AccountInformation.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();

        Button delete= (Button) findViewById(R.id.detail_delete);

        TextView account= (TextView) findViewById(R.id.account_show);
        TextView password= (TextView) findViewById(R.id.password_show);
        TextView CreateTime= (TextView) findViewById(R.id.time_show);
        TextView note= (TextView) findViewById(R.id.note_show);

        account.setText(DtailDatas.get("account").toString());
        password.setText(DtailDatas.get("password").toString());
        CreateTime.setText(DtailDatas.get("time").toString());
        note.setText(DtailDatas.get("note").toString());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("user","id=?",new String[]{idToDel});
                MainActivity.datas.removeAll(MainActivity.datas);
                Intent intent=new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "此功能尚未开放^_^", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //监听Back键按下事件
    @Override
    public void onBackPressed() {
        super.onBackPressed();//调用父类finish方法，

    }
}
