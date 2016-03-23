package com.android.lxf.db.lxf.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.android.lxf.db.lxf.bean.UserBean;
import com.android.lxf.db.lxf.db.LXFDb;
import com.android.lxf.db.lxf.table.UserTable;


public class UserDao  extends BaseDao implements UserTable
{

	private static UserDao mUserDao;

	private UserDao() {

	}

	//获取dao实体
	public static final UserDao getInstance() {
		if (mUserDao == null) {
			mUserDao = new UserDao();
		}
		return mUserDao;
	}

	/**
	 * update user 's name & registration_id
	 * @param bean
	 */
//	public void updateUser(ChinaUser bean){
//		try {
//
//			ContentValues values = new ContentValues();
//			values.put(USER_NAME, bean.name);
//			if(bean.registration_id!=null)
//				values.put(REGISTRATION_ID, bean.registration_id);
//			values.put(USER_SEX, String.valueOf(bean.sex));
//			values.put(USER_PHOTO_URL, bean.photo);
//			mDb.update(TABLE, values, null,null);
//			//20150408 gaoshuai 更新了User之后应该通知刷新UserControl中缓存的User
//            UserControl.refreshCachedUser(bean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// closeDb(null);
//		}
//	}

	public void saveUser(UserBean bean) {
		try {
			getDb().execSQL("delete from " + TABLE);
			ContentValues values = new ContentValues();
			values.put(USER_ID, bean.id);
			values.put(USER_NAME, bean.name);
			values.put(USER_PHOTO_URL, bean.photo);
			values.put(COOKIE, bean.cookie);

			getDb().insert(TABLE, null, values);
			//20150408 gaoshuai 更新了User之后应该通知刷新UserControl中缓存的User
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//  closeDb(null);
		}
	}



	public UserBean getUser() {

		UserBean userData = null;
		Cursor cursor = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ").append(TABLE);
			cursor = getDb().rawQuery(sql.toString(), null);
			//get the first column in table
			while (cursor != null && cursor.moveToNext()) {
				userData = new UserBean();
				userData.id = cursor.getInt(cursor.getColumnIndex(USER_ID));
				userData.name = cursor.getString(cursor.getColumnIndex(USER_NAME));
				userData.cookie = cursor.getString(cursor.getColumnIndex(COOKIE));
				userData.photo=cursor.getString(cursor.getColumnIndex(USER_PHOTO_URL));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closeDb(cursor);
			if(cursor!=null)
				LXFDb.getInstance().closeCursor(cursor);
		}
		return userData;
	}
}
