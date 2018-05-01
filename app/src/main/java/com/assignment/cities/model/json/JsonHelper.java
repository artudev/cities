package com.assignment.cities.model.json;

import java.lang.reflect.Type;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public interface JsonHelper {

	<C> C parseJsonToObject(String json, Class C);

	<T> T parseJsonToObjectList(String json, Type T);

}
