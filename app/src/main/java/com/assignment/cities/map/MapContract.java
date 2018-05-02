package com.assignment.cities.map;

import com.assignment.cities.model.Coordinates;
import com.assignment.cities.parent.BaseFragmentView;
import com.assignment.cities.parent.BasePresenter;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public interface MapContract {

	interface View extends BaseFragmentView {

		void centerMap(float lat, float lon);
	}

	interface Presenter extends BasePresenter {

		void mapReady();

		void setCoordinates(Coordinates coordinates);

		void onStart();

		void onStop();
	}
}
