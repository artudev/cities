package com.assignment.cities.model.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class TestObject {

	@SerializedName("name")
	private String mTestName;

	@SerializedName("value")
	private int mTestValue;

	public TestObject(String testName, int testValue) {
		mTestName = testName;
		mTestValue = testValue;
	}

	public String getTestName() {
		return mTestName;
	}

	public void setTestName(String testName) {
		mTestName = testName;
	}

	public int getTestValue() {
		return mTestValue;
	}

	public void setTestValue(int testValue) {
		mTestValue = testValue;
	}
}
