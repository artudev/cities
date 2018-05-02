package com.assignment.cities.model;

import android.support.annotation.NonNull;

import com.assignment.cities.model.sort.Mappable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class City implements Comparable<City>, Mappable {

	public static final String ITEM_SEPARATOR = ", ";

	@SerializedName("country")
	public final String country;
	@SerializedName("name")
	public final String name;
	@SerializedName("_id")
	public final long id;
	@SerializedName("coord")
	public final Coordinates coordinates;

	public City(String country, String name, long id, Coordinates coordinates) {
		this.country = country;
		this.name = name;
		this.id = id;
		this.coordinates = coordinates;
	}

	public String getValue() {
		return name.concat(ITEM_SEPARATOR).concat(country);
	}

	@Override
	public String getKey() {
		return getValue().toLowerCase();
	}

	@Override
	public String getUniqueKey() {
		return getKey().concat(String.valueOf(id));
	}

	@Override
	public int compareTo(@NonNull City other) {
		return getUniqueKey().compareTo(other.getUniqueKey());
	}
}
