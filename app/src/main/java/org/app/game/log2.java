package org.app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class log2 extends AppCompatActivity {

    TextView text1, text2;
    static EditText etext1;
    static EditText etext2;
    Button btn1, btn2;

    int version = 1;
    static DatabaseOpenHelper helper;
    static SQLiteDatabase database;

    String sql;
    Cursor cursor;

   // static int f1 = Integer.parseInt(getg1());
    static int k1;
    static int k2;
    static int k3;
    static int k4;
    static int k5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log2);
        setTitle("딸기를 찾아라!");

        text1 = (TextView) findViewById(R.id.textID);
        text2 = (TextView) findViewById(R.id.textPW);
        etext1 = (EditText)  findViewById(R.id.editID);
        etext2 = (EditText) findViewById(R.id.editPW);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button_signup);

        helper = new DatabaseOpenHelper(log2.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        btn1.setOnClickListener(new View.OnClickListener() { //확인버튼
            @Override
            public void onClick(View view) {
                String id = etext1.getText().toString();
                String pw = etext2.getText().toString();
                Integer g1 = 0, g2 = 0, g3 = 0, g4 = 0, total = 0;

                if (id.length() == 0 || pw.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast.makeText(log2.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sql = "SELECT id FROM " + DatabaseOpenHelper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                if (cursor.getCount() != 1) {
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(log2.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT pw FROM " + DatabaseOpenHelper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                cursor.moveToNext();
                if (!pw.equals(cursor.getString(0))) {
                    //비밀번호가 틀렸습니다.
                    Toast toast = Toast.makeText(log2.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    //로그인성공
                    Toast toast = Toast.makeText(log2.this, "로그인성공", Toast.LENGTH_SHORT);
                    toast.show();
                    //인텐트 생성 및 호출

                    Intent intent = new Intent(getApplicationContext(), bigStep.class);
                    startActivity(intent);
                    finish();
                }
                cursor.close();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() { //회원가입창으로 가는 버튼
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(log2.this, "회원가입 화면으로 이동", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });



    }

    public static class DatabaseOpenHelper extends SQLiteOpenHelper {

        public static final String tableName = "Users";

        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("tag", "db 생성_db가 없을때만 최초로 실행함");
            createTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        }

        public void createTable(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + tableName + "(id text, pw text, g1 INTEGER, g2 INTEGER, g3 INTEGER, g4 INTEGER, total INTEGER)";
            try {///////////////////********************************
                db.execSQL(sql);
            } catch (SQLException e) {
            }
        }

        public void insertUser(SQLiteDatabase db, String id, String pw, Integer g1, Integer g2, Integer g3, Integer g4, Integer total) {
            Log.i("tag", "회원가입을 했을때 실행함");
            db.beginTransaction();
            try {
                String sql = "INSERT INTO " + tableName + "(id, pw,g1,g2,g3,g4,total)" + "values('" + id + "', '"+ pw +"',0,0,0,0,0)";
                k1=g1;
                k2=g2;
                k3=g3;
                k4=g4;
                k5=total;

                db.execSQL(sql);///////////////////**/*******************************
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }







    }
    public static void updateUsers5(int g1s, int g2s, int g3s, int g4s, int ts) {

        database = helper.getWritableDatabase();
        Cursor cursor;
//        database.execSQL("UPDATE Users SET g1 = '"+ g1s +"', g2 = '"+ g2s +"', g3 = '"+ g3s +"', g4 = '"+ g4s +"', total = '"+ ts +"' WHERE id ='"+etext1.getText().toString()+"'");

        g1s = g1s + k1;
        g2s = g2s + k2;
        g3s = g3s + k3;
        g4s = g4s + k4;
        ts = ts + k5;


        database.execSQL("UPDATE Users SET g1 = '"+g1s+"' , g2 = '"+ g2s +"', g3 = '"+ g3s +"', g4 = '"+ g4s +"', total = '"+ ts +"' WHERE id ='"+etext1.getText().toString()+"'");
        database.close();
        k1=g1s;
        k2=g2s;
        k3=g3s;
        k4=g4s;
        k5=ts;

    }




    public static String getid(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT id FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strID = "";
        while (cursor.moveToNext()){
            strID += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strID;
    }
    public static String getpw(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT pw FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strPW = "";
        while (cursor.moveToNext()){
            strPW += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strPW;
    }
    public static String getg1(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT g1 FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strG1 = "";
        while (cursor.moveToNext()){
            strG1 += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strG1;
    }
    public static String getg2(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT g2 FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strG2 = "";
        while (cursor.moveToNext()){
            strG2 += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strG2;
    }
    public static String getg3(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT g3 FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strG3 = "";
        while (cursor.moveToNext()){
            strG3 += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strG3;
    }
    public static String getg4(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT g4 FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strG4 = "";
        while (cursor.moveToNext()){
            strG4 += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strG4;
    }
    public static String gettotal(){
        database = helper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("SELECT total FROM Users WHERE id ='"+etext1.getText().toString()+"'",null);
        String strTOTAL = "";
        while (cursor.moveToNext()){
            strTOTAL += cursor.getString(0);
        }
        cursor.close();
        database.close();
        return strTOTAL;
    }


}