package com.assignment.cities.map;

import android.util.Log;

import com.assignment.cities.model.Coordinates;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class MapPresenter implements MapContract.Presenter {

	private static final String TAG = MapPresenter.class.getSimpleName();

	private final MapContract.View mView;
	private Coordinates mCoordinates;

	public MapPresenter(MapContract.View view) {
		Log.d(TAG, "MapContract create");
		mView = view;
	}

	@Override
	public void mapReady() {
		mView.centerMap(mCoordinates.lat, mCoordinates.lon);
	}

	@Override
	public void setCoordinates(Coordinates coordinates) {
		mCoordinates = coordinates;
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
