package com.assignment.cities.list;

import android.util.Log;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class ListPresenter implements ListContract.Presenter {

	private static final String TAG = ListPresenter.class.getSimpleName();

	private final ListContract.View mView;

	public ListPresenter(ListContract.View view) {
		Log.d(TAG, "ListPresenter create");
		mView = view;
	}

	@Override
	public void onStart() {
		Log.d(TAG, "onStart");
	}

	@Override
	public void onStop() {
		Log.d(TAG, "onStop");
	}
}
