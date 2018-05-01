package com.assignment.cities.list;

import android.os.Bundle;
import android.util.Log;

import com.assignment.cities.parent.BaseActivity;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class ListActivity extends BaseActivity {

	private static final String TAG = ListActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			Log.d(TAG, "savedInstanceState is NULL");
			//TODO
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}
}
