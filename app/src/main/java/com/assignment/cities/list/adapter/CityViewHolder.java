package com.assignment.cities.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.assignment.cities.R;
import com.assignment.cities.list.listener.OnItemClickListener;
import com.assignment.cities.model.City;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class CityViewHolder extends RecyclerView.ViewHolder {

	private static final String TAG = CityViewHolder.class.getSimpleName();
	private final TextView mTvItem;
	private View mView;
	private City mCity;

	public CityViewHolder(View itemView, OnItemClickListener<City> listener) {
		super(itemView);
		mView = itemView;

		mTvItem = mView.findViewById(R.id.tvItem);

		mView.setOnClickListener(v -> {
			if (listener == null) {
				return;
			}
			listener.itemClicked(getCity());
		});
	}

	protected void bindData(final City city) {
		mCity = city;
		String content = city.getValue();
		mTvItem.setText(content);
	}

	public City getCity() {
		return mCity;
	}
}
