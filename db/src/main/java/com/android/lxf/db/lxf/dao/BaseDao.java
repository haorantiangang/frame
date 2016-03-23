package com.android.lxf.db.lxf.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.lxf.db.lxf.db.LXFDb;


public class BaseDao
{
    private SQLiteDatabase mDb;
    
    public BaseDao() {
        mDb = LXFDb.getInstance().getSqlDateBase();
    }
    
    protected SQLiteDatabase getDb() {
        return mDb;
    }
    
    public void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }
}
