package com.assignment.cities;

import android.app.Application;

import com.assignment.cities.model.CitiesRepository;
import com.assignment.cities.model.City;
import com.assignment.cities.model.assets.AssetsHelperImpl;
import com.assignment.cities.model.json.GsonHelperImpl;
import com.assignment.cities.model.sort.MappableHelperImpl;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class MainApplication extends Application {

	private CitiesRepository mCitiesRepository;
	private MappableHelperImpl<City> mCitiesHelper;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public CitiesRepository getCitiesRepository() {
		if (mCitiesRepository == null) {
			mCitiesRepository = new CitiesRepository(new AssetsHelperImpl(), new GsonHelperImpl());

			mCitiesRepository.initRepository(getApplicationContext());
		}
		return mCitiesRepository;
	}

	public MappableHelperImpl<City> getCitiesHelper() {
		if (mCitiesHelper == null) {
			mCitiesHelper = new MappableHelperImpl<>();
		}
		return mCitiesHelper;
	}
}
