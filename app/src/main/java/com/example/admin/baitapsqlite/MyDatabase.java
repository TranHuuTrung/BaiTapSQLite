package com.example.admin.baitapsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 10/16/2017.
 */

public class MyDatabase extends SQLiteOpenHelper{
    public static final String DBNAME = "sample.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.admin.baitapsqlite";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public MyDatabase(Context context){
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void open(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return ;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void close(){
        if(mDatabase!=null){
            mDatabase.close();
        }
    }

    public void deleteContactAll(){

    }

    public int addContact(Contact contact) {
    }
}

