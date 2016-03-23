package com.android.lxf.db.lxf.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * 数据库的建立
 * 
 * @author lxf
 * 
 */
public class LXFDb
{
    private static final String DATABASE_NAME = "LXFDb";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase mSqlLiteDb;
    private DatabaseHelper mDbHelper;
    private static LXFDb mDb;

    private static Context mContext;

   //在程序开始前需要操作
    public static void init(Context context){
        mContext = context;
    }
    
    /*
     * 打开数据库的操作。 在某些情况下我们会关闭数据库，所以我们先判断数据库是否被关闭了， 如果关闭则重新打开，否则直接使用数据库
     */
    private LXFDb(Context context) {
        // 数据库存在并处于打开状态那么直接使用
        if (mSqlLiteDb != null && mSqlLiteDb.isOpen()) {
            return;
        }
        try {
            // db helper为空则创建一个，避免重复创建
            if (mDbHelper == null) {
                mDbHelper = new DatabaseHelper(context);
            }
            mSqlLiteDb = mDbHelper.getWritableDatabase();
        }
        catch (SQLiteException e) {
            //AppLog.w( "get getWritableDatabase error. info=" + e.getMessage());
            mSqlLiteDb = mDbHelper.getReadableDatabase();
        }
    }
    
    // 创建Database的单例实例
    
    public synchronized static LXFDb getInstance( ) {
        if (mDb == null) {
            mDb = new LXFDb(mContext);
        }
        //AppLog.d( String.format("execute Database getInstance() method %s , Database=" + mDb, System.currentTimeMillis()));
        return mDb;
    }
    
    // 获得database操作数据库的实例
    
    public SQLiteDatabase getSqlDateBase() {
        return mSqlLiteDb;
    }
    
    // 关闭数据库的操作
    
    public void close() {
        mSqlLiteDb.close();
        mDbHelper.close();
    }
    
    // 关闭 Cursor cursor
    public void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }
    
    @Override
    protected void finalize() throws Throwable {
        //AppLog.d("DB CLOSE");
        close();
        super.finalize();
    }
    
    /*
     * 创建内部类DatabaseHelper：创建数据库，创建表，更新表功能
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        
        DatabaseHelper(Context context) {
            
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        // 创建版本中的所有数据表
        
        @Override
        public void onCreate(SQLiteDatabase db) {
            LXFDbHelper.createTables(db);
        }
        
        //数据库的升级策略
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //			LXFDbHelper.dropTables(db, oldVersion, newVersion);
            //			LXFDbHelper.createTables(db);
            LXFDbHelper.updateDb(db, oldVersion, newVersion);
            
        }
        
    }
}
