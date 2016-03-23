package com.android.lxf.toolsutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 *简单的xml数据操作类
 * @author lxf
 *
 * 
 *         测试代码:
 * 
 *         DataStoreUtil.initialize(getApplication());
 *         DataStoreUtil.i().putFloat("testf", 100); Log.e("TAGap",
 *         DataStoreUtil.i().getFloat("testf") + ""); DataStoreUtil.i().clear();
 *         DataStoreUtil.i().putString("testS", "测试"); Log.e("TAGap",
 *         DataStoreUtil.i().getString("testS") + ""); Log.e("TAGap",
 *         DataStoreUtil.i().getFloat("testf") + "");
 */

public class DataStoreUtil
{
    
    private static DataStoreUtil sDataStoreUtil;
    public final String DATASTORE = "datastore";
    protected SharedPreferences mSharedPreferences;
    private static Context mContext;
    /**
     * 拿到操作sharedprefrence的单例类
     * 
     * @param
     * @return
     */

    //注意初始化
    public static void init(Context context){
        mContext  =context;
    }
    public static DataStoreUtil i() {
        if (sDataStoreUtil == null) {
            sDataStoreUtil = new DataStoreUtil();
            sDataStoreUtil.setPath();
        }
        return sDataStoreUtil;
    }
    
    protected void setPath() {
        mSharedPreferences = mContext.getSharedPreferences(DATASTORE, Context.MODE_PRIVATE);
    }
    
    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    
    public boolean getBoolean(String key, Boolean defauleValue) {
        boolean arg = mSharedPreferences.getBoolean(key, defauleValue);
        return arg;
    }
    
    public void putFloat(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    
    public float getFloat(String key, float defaultValue) {
        float arg = mSharedPreferences.getFloat(key, defaultValue);
        return arg;
    }
    
    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    
    public int getInt(String key, int defaultValue) {
        int arg = mSharedPreferences.getInt(key, defaultValue);
        return arg;
    }
    
    public boolean putString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }
    
    public String getString(String key, String defaultValue) {
        
        String arg = mSharedPreferences.getString(key, defaultValue);
        return arg;
    }
    
    public String[] getStringArray(String key) {
        String regularEx = "#~";
        String[] str = null;
        String values;
        values = mSharedPreferences.getString(key, "");
        str = values.split(regularEx);
        return str;
    }
    
    public void putStringArray(String key, String[] values) {
        String regularEx = "#~";
        if (values != null && values.length > 0) {
            StringBuilder sbBuilder = new StringBuilder();
            for (String value : values) {
                sbBuilder.append(value).append(regularEx);
            }
            SharedPreferences.Editor et = mSharedPreferences.edit();
            et.putString(key, sbBuilder.toString());
            et.commit();
        }
    }
    
    public boolean remove(String key) {
        SharedPreferences.Editor et = mSharedPreferences.edit();
        et.remove(key);
        if (et.commit())
            return true;
        else
            return false;
    }
    
    public void clear() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

	/*
	 用于保存整个对象
     */
	public   void putObject(String key, Object obj) {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(key, writeObjToString(obj));
		editor.commit();
	}

	/*
	 用于根据key获取保存的对象
	 */
	public   Object getObject(String key) {
		return  readObjFromeString(mSharedPreferences.getString(key, ""));
	}

    /**
     * 写入对象到字符串
     * @param object
     * @return
     */
    public static String writeObjToString(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            String base64 = new String(Base64.encode(baos.toByteArray(), 0));
            return base64;
        }
        catch (Exception e) {
            return null;
        }

    }
    /**
     * 从字符串读取对象
     * @param objstring
     * @return
     */
    public static Object readObjFromeString(String objstring) {
        try {
            byte[] base64Bytes = Base64.decode(objstring.getBytes(), 0);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
