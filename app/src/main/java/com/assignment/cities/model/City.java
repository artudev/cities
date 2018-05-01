package com.assignment.cities.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class City {

	@SerializedName("country")
	public final int country;
	@SerializedName("name")
	public final String name;
	@SerializedName("_id")
	public final long id;
	@SerializedName("coord")
	public final Coordinates coordinates;

	public City(int country, String name, long id, Coordinates coordinates) {
		this.country = country;
		this.name = name;
		this.id = id;
		this.coordinates = coordinates;
	}
}
