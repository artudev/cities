package com.assignment.cities.list;

import com.assignment.cities.list.listener.OnItemClickListener;
import com.assignment.cities.model.City;
import com.assignment.cities.parent.BaseFragmentView;
import com.assignment.cities.parent.BasePresenter;

import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public interface ListContract {

	interface View extends BaseFragmentView {

		void displayList(List<City> list);

		void setProgressBarVisibility(boolean visible);

		boolean isUnavailable();

		void showMap(City city);
	}

	interface Presenter extends BasePresenter, OnItemClickListener<City> {

		void setQuery(String query);

		void onStart();

		void onStop();
	}
}
