package com.assignment.cities.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.assignment.cities.R;
import com.assignment.cities.model.City;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class CityViewHolder extends RecyclerView.ViewHolder {

	private static final String TAG = CityViewHolder.class.getSimpleName();
	private final TextView mTvItem;
	private View mView;

	public CityViewHolder(View itemView) {
		super(itemView);
		mView = itemView;

		mTvItem = (TextView) mView.findViewById(R.id.tvItem);
	}

	protected void bindData(final City city) {
		Log.d(TAG, "bindData");

		//TODO
	}
}
