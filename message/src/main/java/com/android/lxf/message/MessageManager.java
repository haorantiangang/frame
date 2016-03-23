package com.android.lxf.message;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作者：lxf on 16/2/19 09:26
 * 邮箱：1173074500@qq.com
 */
public class MessageManager {
    Handler mHandler; //初始化一个handler用于进行子线程的处理
	private SparseArray<List<WeakReference<IEvent>>> mNotificationMap = new SparseArray<List<WeakReference<IEvent>>>();

	private static class SingletonClassInstance {
		private static final MessageManager instance = new MessageManager();
	}

	public static MessageManager getInstance() {
		return SingletonClassInstance.instance;
	}

	private MessageManager() {
		mHandler = new Handler(Looper.getMainLooper());
	}

	/**
	 * 注册MC消息 , 每个<b>类</b>只能注册一次, (以类名来区分, 而不是实例), 适用于Activity等
	 *
	 * @param notify
	 * @param mcmessageid
	 */
	public void register(IEvent notify, int mcmessageid) {
		register(notify, mcmessageid, true);
	}

	/**
	 * 注册MC消息 , 同一个类不同实例都可以收到消息
	 *
	 * @param notify
	 * @param mcmessageid
	 */
	public void registerAllInstance(IEvent notify, int mcmessageid) {
		register(notify, mcmessageid, false);
	}

	/**
	 * 注册MC消息.
	 *
	 * @param notify
	 * @param mcmessageid
	 *            消息ID 在MCManager中
	 * @param removeOtherInstance
	 *            是否移除类名相同的其他实例, 如果是Activity很可能要移除
	 */
	public void register(IEvent notify, int mcmessageid, boolean removeOtherInstance) {
		List<WeakReference<IEvent>> messages = mNotificationMap.get(mcmessageid);
		if (messages == null) {
			messages = new ArrayList<WeakReference<IEvent>>();
			mNotificationMap.put(mcmessageid, messages);
		}

		for (Iterator<WeakReference<IEvent>> iterator = messages.iterator(); iterator.hasNext();) {
			WeakReference<IEvent> wNotify = iterator.next();
			if (wNotify != null) {
				IEvent notifyInList = wNotify.get();
				if (notifyInList == null) {
					iterator.remove();
					continue;
				}
				if (removeOtherInstance) {
					if (notify.getClass().getSimpleName().equals(notifyInList.getClass().getSimpleName())) {
						iterator.remove();
					}
				}

			}
		}
		messages.add(new WeakReference<IEvent>(notify));
	}

	/**
	 * 反注册 ,以类名为key 取消Activity的监听
	 *
	 * @param notify
	 * @param mcmessageid
	 */
	public void unregister(IEvent notify, int mcmessageid) {
		List<WeakReference<IEvent>> messages = mNotificationMap.get(mcmessageid);
		if (messages != null) {
			for (Iterator<WeakReference<IEvent>> iterator = messages.iterator(); iterator.hasNext();) {
				WeakReference<IEvent> wNotify = iterator.next();
				if (wNotify != null) {
					IEvent notify2 = wNotify.get();
					if (notify2 == null) {
						iterator.remove();
						continue;
					}
					//一个类, 只能注册一次!
					if (notify2.getClass().getSimpleName().equals(notify.getClass().getSimpleName())) {
						iterator.remove();
					}
				}
			}
		}
	}
	/**
	 * 发送Message
	 *
	 * @param sdmessage
	 */
	public void postM(SDMessage sdmessage) {
		List<WeakReference<IEvent>> messages = mNotificationMap.get(sdmessage.notificationID);
		if (messages != null) {
			for (Iterator<WeakReference<IEvent>> iterator = messages.iterator(); iterator.hasNext();) {
				WeakReference<IEvent> wNotify = iterator.next();
				if (wNotify != null) {
					IEvent notify = wNotify.get();
					if (notify == null) {
						iterator.remove();
					}
					else {
						try {
							postEvent(notify,sdmessage);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
	}

	private void postEvent(final IEvent notify,final SDMessage sdmessage){
		if (Looper.getMainLooper() == Looper.myLooper()) //在主线程中调用
		{
			notify.onSDEvent(sdmessage);
		}else //在子线程中调用
		{
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					notify.onSDEvent(sdmessage);
				}
			});
		}
	}

//	//注册类需要继承
//	public interface IEvent
//	{
//		public void onSDEvent(SDMessage mcmessage);
//	}
//    //传递的信息封装
//	public static  class SDMessage
//	{
//		public int notificationID;
//		public Object extObj;
//
//		public SDMessage(int id, Object extObj) {
//			this.notificationID = id;
//			this.extObj = extObj;
//		}
//		public SDMessage(int id) {
//			this(id, null);
//		}
//	}
	/**
	 * 在退出的时候清空通知map
	 */
	public void cleanUpOnExit() {
		for (int i = 0; i < mNotificationMap.size(); i++) {
			List<WeakReference<IEvent>> lists = mNotificationMap.get(i);
			if (lists != null) {
				lists.clear();
			}
		}
		mNotificationMap.clear();
	}
}