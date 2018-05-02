package com.assignment.cities.model.sort;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.assignment.cities.model.callback.OnCompleteCallback;
import com.assignment.cities.model.listener.OnTreeChangeListener;
import com.assignment.cities.model.handler.CompletableHandler;

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
	public void parseItems(List<T> items, boolean doInBackground) {
		Log.d(TAG, "parseItems");
		if (!canReload()) {
			return;
		}
		if (doInBackground) {
			parseBackground(items);
		} else {
			setTreeMap(createTreeMap(items));
		}
	}

	private void parseBackground(List<T> items) {
		Log.d(TAG, "parseBackground");

		CompletableHandler handler = new CompletableHandler(Looper.getMainLooper(), getTreeInitCallback());

		Runnable runnable = () -> {
			setTreeMap(createTreeMap(items));
			Message message = CompletableHandler.getCompleteMessage();
			handler.sendMessage(message);
		};

		mInitThread = new Thread(runnable);
		mInitThread.start();
	}

	private TreeMap<String, T> createTreeMap(List<T> items) {
		long start = System.currentTimeMillis();

		TreeMap<String, T> treeMap = new TreeMap<>();
		for (T t : items) {
			treeMap.put(t.getUniqueKey(), t);
		}

		long end = System.currentTimeMillis();
		Log.d(TAG, "init time: " + (end - start));

		return treeMap;
	}

	@Override
	public void setTreeMap(TreeMap<String, T> treeMap) {
		Log.d(TAG, "setTreeMap: " + (treeMap != null));
		synchronized (mListLock) {
			mTreeMap = treeMap;
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

	private boolean canReload() {
		return !isRunning();
	}

	@Override
	public boolean isGenerated() {
		return mTreeMap != null;
	}

	public boolean isRunning() {
		return mInitThread != null && mInitThread.isAlive();
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
}
