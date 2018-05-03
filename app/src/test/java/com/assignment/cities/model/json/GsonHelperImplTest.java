package com.assignment.cities.model.json;

import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class GsonHelperImplTest {

	private GsonHelperImpl mGsonHelper;

	@Before
	public void setUp() {
		mGsonHelper = new GsonHelperImpl();
	}

	@Test
	public void parseJsonToObject() {

		TestObject source = new TestObject("a", 1);

		TestObject object =
				mGsonHelper.parseJsonToObject(getTestObjectJson(source), TestObject.class);

		Assert.assertNotNull(object);
		Assert.assertEquals(source.getTestName(), object.getTestName());
		Assert.assertEquals(source.getTestValue(), object.getTestValue());
	}

	@Test
	public void parseJsonToObjectList() {
		Type listType = new TypeToken<ArrayList<TestObject>>() {
		}.getType();

		ArrayList<TestObject> sources = new ArrayList<>();
		TestObject source1 = new TestObject("a", 1);
		sources.add(source1);
		TestObject source2 = new TestObject("b", 2);
		sources.add(source2);

		ArrayList<TestObject> objects =
				mGsonHelper.parseJsonToObjectList(getTestObjectListJson(sources), listType);

		Assert.assertNotNull(objects);
		Assert.assertEquals(sources.size(), objects.size());
		Assert.assertEquals(sources.get(0).getTestName(), objects.get(0).getTestName());
		Assert.assertEquals(sources.get(0).getTestValue(), objects.get(0).getTestValue());
	}

	private String getTestObjectJson(TestObject testObject) {
		return "{\"name\": \"" + testObject.getTestName() + "\",\"value\": " +
			   testObject.getTestValue() + "}";
	}

	private String getTestObjectListJson(List<TestObject> objects) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		for (TestObject object : objects) {
			stringBuilder.append(getTestObjectJson(object)).append(",");
		}
		if (objects.size() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		stringBuilder.append("]");

		return stringBuilder.toString();
	}
}
