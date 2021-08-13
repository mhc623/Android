package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

        final Button createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });

        /*
        增添数据
         */
        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","水浒传");
                values.put("author","施耐庵");
                values.put("pages",499);
                values.put("price",69);
                //values.put("page",499);
                db.insert("Book",null,values);
                values.clear();
                values.put("name","红楼梦");
                values.put("author","曹雪芹");
                values.put("pages",540);
                values.put("price",88);
                db.insert("Book",null,values);
            }
        });

        /*
        更改数据
         */

        Button updatedata = findViewById(R.id.update_data);
        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",69);
                db.update("Book",values,"name = ?",new String[] { "红楼梦" });
            }
        });

        /*
        删除数据
         */

        Button deleteButton = (Button)findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","price > ?", new String[]{"70"}) ;
            }
        });

        /*
        查询数据
         */

        Button queryButton = (Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainAtivity", "书名是 " + name);
                        Log.d("MainAtivity", "作者为 " + author);
                        Log.d("MainAtivity", "有 " + pages + "页");
                        Log.d("MainAtivity", "价格是 " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        });
    }
}
