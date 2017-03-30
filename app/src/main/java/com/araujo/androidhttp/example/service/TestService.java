package com.araujo.androidhttp.example.service;

import com.araujo.androidhttp.example.model.Ip;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import io.araujo.androidhttp.webservice.Method;
import io.araujo.androidhttp.webservice.WebService;

/**
 * Created by jorge.araujo on 1/29/2016.
 */

public class TestService extends WebService<Void, Ip, Void> {

	@Override
	public MediaType consumes() {
		return MediaType.APPLICATION_JSON_TYPE;
	}

	@Override
	public Method method() {
		return Method.GET;
	}

	@Override
	public String path(Void data) {
		return "https://api.ipify.org/";
	}

	@Override
	public Map<String, String> params(Void data) {
		Map<String, String> map = super.params(data);
		map.put("format", "json");
		return map;
	}
}
