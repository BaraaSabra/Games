package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DB_name="Games_db";
    public static final int DB_VERSION=11;//لما نغير قميتة بدخل ع الداله التانية داله التحديث
    public  static  String Game_TB_NAME="Game";
    public  static  String Game_CLM_SCORE="score";
    public  static  String Game_CLM_NAME="name";
    public  static  String Game_CLM_DATE="date";
    public  static  final String Game_CLM_ID="id";







    public Database(Context context){

        super(context,DB_name,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //يتم استدعائها عند انشاء الداتابيس

        sqLiteDatabase.execSQL("CREATE TABLE "+ Game_TB_NAME+" ( " +Game_CLM_ID+" INTEGER primary key autoincrement," +Game_CLM_SCORE+" INTEGER, "+Game_CLM_DATE+" TEXT, "+Game_CLM_NAME+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       //يتم استدعائها عند تحديث الداتابيس
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +Game_TB_NAME);
        onCreate(sqLiteDatabase);

    }
    //داله الاضافة
    public  boolean insertGame(Games game){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Game_CLM_NAME,game.getName());
        values.put(Game_CLM_SCORE,game.getScore());
        values.put(Game_CLM_DATE,game.getDate());

        long resuit=db.insert(Game_TB_NAME, null ,values);
        return resuit !=-1;

    }
    public ArrayList<Games> getAllGames(){

            ArrayList<Games> games = new ArrayList<>();
            SQLiteDatabase database=getReadableDatabase();
            Cursor cursor=database.rawQuery("select * from "+ Game_TB_NAME,null);
            if (cursor.moveToFirst()){
                do {
                    String name=cursor.getString(cursor.getColumnIndexOrThrow(Game_CLM_NAME));
                    int scre=cursor.getInt(cursor.getColumnIndexOrThrow(Game_CLM_SCORE));
                    String date=cursor.getString(cursor.getColumnIndexOrThrow(Game_CLM_DATE));

                    Games games1=new Games(scre,name,date);
                    games.add(games1);

                }while (cursor.moveToNext());
                cursor.close();
            }

            return  games;
        }



            public boolean deletegame(){
        SQLiteDatabase database=getReadableDatabase();
        long result=database.delete(Game_TB_NAME,null,null);
                Log.d("result",result+"");
                return  result!=-1;
            }
            public String lastdate(){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from "+Game_TB_NAME,null);
        if (cursor.moveToLast()){
            String date=cursor.getString(cursor.getColumnIndexOrThrow(Game_CLM_DATE));
            return date;
        }
        return  lastdate();
            }


        }
