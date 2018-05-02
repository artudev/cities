package com.assignment.cities.model;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.assignment.cities.model.assets.AssetHelper;
import com.assignment.cities.model.callback.OnCompleteCallback;
import com.assignment.cities.model.handler.CompletableHandler;
import com.assignment.cities.model.json.JsonHelper;
import com.assignment.cities.model.listener.OnRepoChangeListener;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class CitiesRepository {

	private static final String TAG = CitiesRepository.class.getSimpleName();

	private static final String CITIES_FILE = "cities.json";

	private final AssetHelper mAssetHelper;
	private final JsonHelper mJsonHelper;

	private List<City> mCities;

	private List<OnRepoChangeListener> mOnRepoChangeListeners;

	private final Object mListLock = new Object();
	private Thread mInitThread;

	public CitiesRepository(AssetHelper assetHelper, JsonHelper jsonHelper) {
		mAssetHelper = assetHelper;
		mJsonHelper = jsonHelper;
		mOnRepoChangeListeners = new ArrayList<>();
		mCities = new ArrayList<>();
	}

	public void addListener(OnRepoChangeListener listener) {
		mOnRepoChangeListeners.add(listener);
	}

	public void removeListener(OnRepoChangeListener listener) {
		mOnRepoChangeListeners.remove(listener);
	}

	public void initRepository(Context context, boolean doInBackground) {
		Log.d(TAG, "initRepository");
		if (!shouldReload()) {
			return;
		}
		if (doInBackground) {
			initRepositoryBackground(context);
		} else {
			retrieveRepository(context, CITIES_FILE);
		}
	}

	private void initRepositoryBackground(Context context) {

		Log.d(TAG, "initRepositoryBackground");

		CompletableHandler handler =
				new CompletableHandler(Looper.getMainLooper(), getRepoInitCallback());

		Runnable runnable = () -> {
			retrieveRepository(context, CITIES_FILE);
			Message message = CompletableHandler.getCompleteMessage();
			handler.sendMessage(message);
		};

		mInitThread = new Thread(runnable);
		mInitThread.start();
	}

	private void retrieveRepository(Context context, String fileName) {

		long start = System.currentTimeMillis();

		String json = mAssetHelper.retrieveTextAsset(context, fileName);

		Type listType = new TypeToken<List<City>>() {
		}.getType();

		List<City> cities = mJsonHelper.parseJsonToObjectList(json, listType);
		setCities(cities);

		long end = System.currentTimeMillis();
		Log.d(TAG, "init time: " + (end - start));
	}

	private OnCompleteCallback getRepoInitCallback() {
		return () -> {
			Log.d(TAG, "repoInitCallback");

			if (mOnRepoChangeListeners == null) {
				return;
			}

			for (OnRepoChangeListener listener : mOnRepoChangeListeners) {
				listener.onRepositoryChanged();
			}
		};
	}

	public void setCities(List<City> cities) {
		Log.d(TAG, "setCities");
		synchronized (mListLock) {
			mCities.clear();
			mCities.addAll(cities);
		}
	}

	public List<City> getRawCities() {
		Log.d(TAG, "getRawCities");
		return mCities;
	}

	/**
	 * In case of downloading data from server we could add some custom logic.
	 * For example in our case we have static local storage so only one load is needed
	 * when list is empty.
	 */
	private boolean shouldReload() {
		return mCities.isEmpty() && !isRunning();
	}

	public boolean isGenerated() {
		return !mCities.isEmpty();
	}

	public boolean isRunning() {
		return mInitThread != null && mInitThread.isAlive();
	}
}
