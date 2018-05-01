package com.assignment.cities.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class Coordinates {

	@SerializedName("lon")
	public final float lon;
	@SerializedName("lat")
	public final float lat;

	public Coordinates(float lon, float lat) {
		this.lon = lon;
		this.lat = lat;
	}
}
