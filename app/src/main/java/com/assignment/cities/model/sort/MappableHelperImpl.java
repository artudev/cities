package com.assignment.cities.model.sort;

import android.util.Log;

import com.assignment.cities.model.callback.OnCompleteCallback;
import com.assignment.cities.model.listener.OnTreeChangeListener;
import com.assignment.cities.model.runnable.CompletableHandler;
import com.assignment.cities.model.runnable.InitTreeMapRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class MappableHelperImpl<T extends Mappable> implements MappableHelper<T> {

	private static final String TAG = MappableHelperImpl.class.getSimpleName();
	private TreeMap<String, T> mTreeMap;
	private Thread mInitThread;

	private List<OnTreeChangeListener> mOnTreeChangeListeners;
	private final Object mListLock = new Object();

	public MappableHelperImpl() {
		mOnTreeChangeListeners = new ArrayList<>();
	}

	public void addListener(OnTreeChangeListener listener) {
		mOnTreeChangeListeners.add(listener);
	}

	public void removeListener(OnTreeChangeListener listener) {
		mOnTreeChangeListeners.remove(listener);
	}

	@Override
	public void initTree(List<T> cities) {
		Log.d(TAG, "initTree");
		if (shouldReload()) {
			initTreeBackground(cities);
		}
	}

	private void initTreeBackground(List<T> cities) {
		Log.d(TAG, "initTreeBackground");

		CompletableHandler handler = new CompletableHandler(getTreeInitCallback());

		InitTreeMapRunnable<T> runnable = new InitTreeMapRunnable<>(cities, this, handler);
		mInitThread = new Thread(runnable);
		mInitThread.start();
	}

	private OnCompleteCallback getTreeInitCallback() {
		return () -> {
			Log.d(TAG, "treeInitCallback");

			if (mOnTreeChangeListeners == null) {
				return;
			}

			for (OnTreeChangeListener listener : mOnTreeChangeListeners) {
				listener.onTreeChanged();
			}
		};
	}

	@Override
	public void setTreeMap(TreeMap<String, T> cityTreeMap) {
		synchronized (mListLock) {
			mTreeMap = cityTreeMap;
		}
	}

	@Override
	public List<T> getItems() {
		return getItems("");
	}

	@Override
	public List<T> getItems(String key) {

		long start = System.currentTimeMillis();

		if (mTreeMap == null) {
			return new ArrayList<>();
		}

		SortedMap<String, T> keyMap;

		key = key.toLowerCase();

		if (key.equals("")) {
			keyMap = mTreeMap.tailMap(key);
		} else {
			String nextKey = getNextKey(key);
			keyMap = mTreeMap.subMap(key, true, nextKey, false);
		}

		List<T> cities = new ArrayList<>(keyMap.values());

		long end = System.currentTimeMillis();
		Log.d(TAG, "time: " + (end - start));

		return cities;
	}

	public String getNextKey(String key) {
		int charValue = key.charAt(key.length() - 1);
		String nextChar = String.valueOf((char) (charValue + 1));
		String nextKey = key.substring(0, key.length() - 1) + nextChar;
		return nextKey.toLowerCase();
	}

	private boolean shouldReload() {
		return mTreeMap == null && !isRunning();
	}

	@Override
	public boolean isGenerated() {
		return mTreeMap != null && !isRunning();
	}

	public boolean isRunning() {
		return mInitThread != null && mInitThread.isAlive();
	}
}
