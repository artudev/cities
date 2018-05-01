package com.assignment.cities.model.json;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class GsonHelperImpl implements JsonHelper {

	@Override
	public <C> C parseJsonToObject(String json, Class C) {
		if (!json.isEmpty()) {
			Gson gson = new Gson();
			return (C) gson.fromJson(json, C);
		}
		return null;
	}

	@Override
	public <T> T parseJsonToObjectList(String json, Type T) {
		if (!json.isEmpty()) {
			Gson gson = new Gson();
			return (T) gson.fromJson(json, T);
		}
		return null;
	}
}
