package com.assignment.cities.model.runnable;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.assignment.cities.model.sort.MappableHelper;
import com.assignment.cities.model.sort.Mappable;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class InitTreeMapRunnable<T extends Mappable> implements Runnable {

	private List<T> mItems;
	private final MappableHelper<T> mMappableHelper;
	private final Handler mHandler;

	public InitTreeMapRunnable(List<T> items, MappableHelper<T> mappableHelper, Handler handler) {
		mItems = items;
		mMappableHelper = mappableHelper;
		mHandler = handler;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		TreeMap<String, T> treeMap = new TreeMap<>();
		for (T t : mItems) {
			treeMap.put(t.getUniqueKey(), t);
		}
		mMappableHelper.setTreeMap(treeMap);

		long end = System.currentTimeMillis();
		Log.d("TreeMap", "init time: " + (end - start));

		Message message = CompletableHandler.getCompleteMessage();
		mHandler.sendMessage(message);
	}
}
