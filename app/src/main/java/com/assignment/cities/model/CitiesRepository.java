package com.assignment.cities.model;

import android.content.Context;

import com.assignment.cities.model.assets.AssetHelper;
import com.assignment.cities.model.json.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class CitiesRepository {

	private static final String CITIES_FILE = "cities.json";

	private final AssetHelper mAssetHelper;
	private final JsonHelper mJsonHelper;
	private List<City> mCities;

	public CitiesRepository(AssetHelper assetHelper, JsonHelper jsonHelper) {
		mAssetHelper = assetHelper;
		mJsonHelper = jsonHelper;
		mCities = new ArrayList<>();
	}

	public void retrieveCities(Context context, CitiesCallback callback) {
		if (shouldReload()) {
			retrieveCitiesBackground(context, callback);
		} else {
			callback.onListRetrieved(mCities);
		}
	}

	private void retrieveCitiesBackground(Context context, CitiesCallback callback) {
		CitiesCallback repoCallback = cities -> {
			setCities(cities);
			callback.onListRetrieved(cities);
		};

		GetCitiesTask getCitiesTask =
				new GetCitiesTask(context, mAssetHelper, mJsonHelper, repoCallback);
		getCitiesTask.execute(CITIES_FILE);
	}

	public void setCities(List<City> cities) {
		mCities.clear();
		mCities.addAll(cities);
	}

	/**
	 * In case of downloading data from server we could add some custom logic.
	 * For example in our case we have static local storage so only one load is needed
	 * when list is empty.
	 */
	private boolean shouldReload() {
		return mCities.isEmpty();
	}
}
