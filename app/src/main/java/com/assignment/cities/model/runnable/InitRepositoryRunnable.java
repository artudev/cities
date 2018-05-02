package com.assignment.cities.model.runnable;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.assignment.cities.model.CitiesRepository;
import com.assignment.cities.model.City;
import com.assignment.cities.model.assets.AssetHelper;
import com.assignment.cities.model.json.JsonHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class InitRepositoryRunnable implements Runnable {

	private final CitiesRepository mCitiesRepository;
	private final AssetHelper mAssetHelper;
	private final JsonHelper mJsonHelper;
	private final String mFileName;
	private final Context mContext;
	private final Handler mHandler;

	public InitRepositoryRunnable(CitiesRepository citiesRepository, AssetHelper assetHelper,
			JsonHelper jsonHelper, String fileName, Context context, Handler handler) {
		mCitiesRepository = citiesRepository;
		mAssetHelper = assetHelper;
		mJsonHelper = jsonHelper;
		mFileName = fileName;
		mContext = context;
		mHandler = handler;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		String json = mAssetHelper.retrieveTextAsset(mContext, mFileName);

		Type listType = new TypeToken<List<City>>() {
		}.getType();

		List<City> cities = mJsonHelper.parseJsonToObjectList(json, listType);
		mCitiesRepository.setCities(cities);

		long end = System.currentTimeMillis();
		Log.d("Repository", "init time: " + (end - start));

		Message message = CompletableHandler.getCompleteMessage();
		mHandler.sendMessage(message);
	}
}
