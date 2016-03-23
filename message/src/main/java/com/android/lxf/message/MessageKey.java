package com.android.lxf.message;

/**
 * 作者：lxf on 16/2/19 09:26
 * 邮箱：1173074500@qq.com
 * 说明：用于生成message的key  唯一标示 在主程序中可以继承这个类
 */
public class MessageKey
{
    private static int mMessageBase = 0;
    
    private static int generateMessageID() {
        return mMessageBase++;
    }

    //测试 主线程 key

    public final static int MessageMainTH = generateMessageID();

    //测试 子线程 key

    public final static int MessageSubTH = generateMessageID();
}
