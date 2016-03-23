package com.android.lxf.db.lxf.table;

public interface UserTable
{
     String TABLE = "user";
      String USER_ID = "user_id";
     String USER_NAME = "user_name";
      String REGISTRATION_ID = "registration_id";
     String ROOM_ID = "roomid";
     String COOKIE = "cookie";
     String AREA_ID = "area_id";
      String USER_PHOTO_URL = "photourl";
      String USER_SEX = "sex";
  String SQL_TABLE_DROP = String.format("DROP TABLE IF EXISTS %s;", TABLE);
//    public static final String SQL_TABLE_CREATE = new StringBuilder().append("CREATE TABLE IF NOT EXISTS ").
//    		append(TABLE).append("(").append(USER_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,").
//    		append(USER_NAME).append(" VARCHAR(20), ").append(COOKIE).
//    		append(" VARCHAR(40), ").append(AREA_ID).append(" INT, ").
//    		append(REGISTRATION_ID).append(" VARCHAR(32) ").append(");")
//    		.toString();
//    
    
     String SQL_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+TABLE+"("+USER_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT,"+USER_NAME+" VARCHAR(20), "+COOKIE+" VARCHAR(40), "
    		+AREA_ID+" INT, "+REGISTRATION_ID+" VARCHAR(32), "+ USER_PHOTO_URL+" VARCHAR(200) ,"+USER_SEX+" VARCHAR(10) "+   ");";
    
}