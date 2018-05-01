package com.assignment.cities.model;

import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public interface CitiesCallback {

	void onListRetrieved(List<City> cities);
}
