package com.araujo.androidhttp.example;

import com.orm.SugarApp;

import io.araujo.androidhttp.webservice.request.volley.AndroidHttp;

/**
 * Created by jorge.araujo on 3/30/2017.
 */

public class MyApplication extends SugarApp {

	@Override
	public void onCreate() {
		super.onCreate();
		AndroidHttp.init(this, null, null);
	}
}
