package com.assignment.cities.list;

import android.util.Log;

import com.assignment.cities.model.CitiesRepository;
import com.assignment.cities.model.City;
import com.assignment.cities.model.listener.OnRepoChangeListener;
import com.assignment.cities.model.listener.OnTreeChangeListener;
import com.assignment.cities.model.sort.MappableHelperImpl;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class ListPresenter
		implements ListContract.Presenter, OnRepoChangeListener, OnTreeChangeListener {

	private static final String TAG = ListPresenter.class.getSimpleName();

	private final ListContract.View mView;
	private CitiesRepository mCitiesRepository;
	private MappableHelperImpl<City> mCityHelper;
	private String mQuery;

	public ListPresenter(ListContract.View view, CitiesRepository citiesRepository,
			MappableHelperImpl<City> cityHelper) {
		Log.d(TAG, "ListPresenter create");
		mCitiesRepository = citiesRepository;
		mCityHelper = cityHelper;

		mQuery = "";
		mView = view;
	}

	@Override
	public void setQuery(String query) {
		mQuery = query;
		displayList();
	}

	private void displayList() {
		Log.d(TAG, "displayList");
		mView.setProgressBarVisibility(true);

		if (!mCitiesRepository.isGenerated()) {
			Log.d(TAG, "!mCitiesRepository.isGenerated()");
			return;
		}

		if (!mCityHelper.isGenerated()) {
			Log.d(TAG, "!mCityHelper.isGenerated()");
			return;
		}

		mView.displayList(mCityHelper.getItems(mQuery));
		mView.setProgressBarVisibility(false);
	}

	@Override
	public void onRepositoryChanged() {
		Log.d(TAG, "onRepositoryChanged");
		mCityHelper.parseItems(mCitiesRepository.getRawCities(), true);
	}

	@Override
	public void onTreeChanged() {
		Log.d(TAG, "onTreeChanged");
		if (!mView.isUnavailable()) {
			displayList();
		}
	}

	@Override
	public void onStart() {
		Log.d(TAG, "onStart");
		mCitiesRepository.addListener(this);
		mCityHelper.addListener(this);

		displayList();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "onStop");
		mCitiesRepository.removeListener(this);
		mCityHelper.removeListener(this);
	}
}
