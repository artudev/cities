package com.assignment.cities.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.cities.R;
import com.assignment.cities.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

	private static final String TAG = CityAdapter.class.getSimpleName();

	private List<City> mCities;

	public CityAdapter() {
		mCities = new ArrayList<>();
	}

	public void setCities(List<City> cities) {
		mCities.clear();
		mCities.addAll(cities);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView =
				LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
		return new CityViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
		holder.bindData(mCities.get(position));
	}

	@Override
	public int getItemCount() {
		return mCities.size();
	}
}
