package com.android.lxf.db.lxf.db;


import android.database.sqlite.SQLiteDatabase;

import com.android.lxf.db.lxf.table.UserTable;


/**
 * 
 * 数据库创建，删除和数据迁移和升级策略的操作
 * 
 * @author lxf
 * 
 */
public class LXFDbHelper
{
    
    /*
     * 初始安装创建表，根据需求建立表格
     */
    public static void createTables(SQLiteDatabase db) {

        db.execSQL(UserTable.SQL_TABLE_CREATE);

    }
    
    /*
     * 初始安装删除表
     */
    public static void dropTables(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(UserTable.SQL_TABLE_DROP);

    }
    
    /*
     * 删除单表的操作
     */
    public static void dropTable(String tableName, SQLiteDatabase db) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", tableName));
    }
    
    //数据库的升级策略
    public static void updateDb(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            int nextVersion = i + 1;
            switch (nextVersion) {
                case 2:

                    break;
                case 3:
                    break;
                default:
                    break;
            }
            
        }
    }
    
}
