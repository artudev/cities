package com.assignment.cities.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class Coordinates implements Parcelable {

	@SerializedName("lon")
	public final float lon;
	@SerializedName("lat")
	public final float lat;

	public Coordinates(float lon, float lat) {
		this.lon = lon;
		this.lat = lat;
	}

	protected Coordinates(Parcel in) {
		lon = in.readFloat();
		lat = in.readFloat();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeFloat(lon);
		dest.writeFloat(lat);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Coordinates> CREATOR =
			new Parcelable.Creator<Coordinates>() {
				@Override
				public Coordinates createFromParcel(Parcel in) {
					return new Coordinates(in);
				}

				@Override
				public Coordinates[] newArray(int size) {
					return new Coordinates[size];
				}
			};
}
