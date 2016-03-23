package com.android.lxf.test.bean;

import java.util.List;

/**
 * 作者：lxf on 16/3/2 15:37
 * 邮箱：1173074500@qq.com
 * 说明： 获取的bean格式
 */
public class HttpGetBean extends BaseBean<List<HttpGetBean>> {
	public String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String home;
	public int age;
}
