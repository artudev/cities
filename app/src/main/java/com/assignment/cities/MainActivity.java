package com.assignment.cities;

import android.os.Bundle;
import android.util.Log;

import com.assignment.cities.list.ListFragment;
import com.assignment.cities.parent.BaseActivity;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class MainActivity extends BaseActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			Log.d(TAG, "savedInstanceState is NULL");
			setContent(new ListFragment());
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}
}
