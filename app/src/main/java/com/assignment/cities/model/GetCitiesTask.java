package com.assignment.cities.model;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.cities.model.assets.AssetHelper;
import com.assignment.cities.model.json.JsonHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class GetCitiesTask extends AsyncTask<String, Integer, List<City>> {

	private Context mContext;
	private AssetHelper mAssetHelper;
	private JsonHelper mJsonHelper;
	private CitiesCallback mCallback;

	public GetCitiesTask(Context context, AssetHelper assetHelper, JsonHelper jsonHelper,
			CitiesCallback callback) {
		mContext = context;
		mAssetHelper = assetHelper;
		mJsonHelper = jsonHelper;
		mCallback = callback;
	}

	@Override
	protected List<City> doInBackground(String... params) {
		String json = mAssetHelper.retrieveTextAsset(mContext, params[0]);

		Type listType = new TypeToken<List<City>>() {
		}.getType();

		List<City> cities = mJsonHelper.parseJsonToObjectList(json, listType);

		return cities;
	}

	@Override
	protected void onPostExecute(List<City> cities) {
		mCallback.onListRetrieved(cities);
	}
}
