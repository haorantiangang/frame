package com.android.lxf.message;

public  class SDMessage
	{
		public int notificationID;
		public Object extObj;

		public SDMessage(int id, Object extObj) {
			this.notificationID = id;
			this.extObj = extObj;
		}
		public SDMessage(int id) {
			this(id, null);
		}
	}