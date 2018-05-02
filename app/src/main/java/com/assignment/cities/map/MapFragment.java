package com.assignment.cities.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.cities.R;
import com.assignment.cities.model.City;
import com.assignment.cities.model.Coordinates;
import com.assignment.cities.parent.BaseFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class MapFragment extends BaseFragment implements MapContract.View, OnMapReadyCallback {

	private static final String TAG = MapFragment.class.getSimpleName();

	public static final String ARG_COORDS = "coords";
	private static final float ZOOM = 10F;

	private GoogleMap mMap;

	private MapContract.Presenter mPresenter;

	public static MapFragment newInstance(City city) {
		MapFragment fragment = new MapFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_COORDS, city.coordinates);
		fragment.setArguments(args);
		return fragment;
	}

	public MapFragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_map, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mPresenter = new MapPresenter(this);

		Coordinates coordinates = getArguments().getParcelable(ARG_COORDS);
		mPresenter.setCoordinates(coordinates);

		SupportMapFragment mapFragment =
				(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		mPresenter.mapReady();
	}

	@Override
	public void centerMap(float lat, float lon) {
		CameraPosition cameraPosition =
				new CameraPosition.Builder().target(new LatLng(lat, lon)).zoom(ZOOM).build();
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		mMap.moveCamera(cameraUpdate);
	}

	@Override
	public void onStart() {
		super.onStart();
		mPresenter.onStart();
	}

	@Override
	public void onStop() {
		mPresenter.onStop();
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.d(TAG, "onDestroyView");
		super.onDestroyView();
	}
}
