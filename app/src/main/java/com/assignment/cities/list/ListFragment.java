package com.assignment.cities.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.cities.R;
import com.assignment.cities.list.adapter.CityAdapter;
import com.assignment.cities.model.City;
import com.assignment.cities.parent.BaseFragment;

import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class ListFragment extends BaseFragment implements ListContract.View {

	private static final String TAG = ListFragment.class.getSimpleName();

	private RecyclerView mRvList;
	private ListContract.Presenter mPresenter;
	private CityAdapter mCityAdapter;

	public ListFragment() {
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

		View view = inflater.inflate(R.layout.fragment_list, container, false);
		mRvList = view.findViewById(R.id.rvList);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mPresenter = new ListPresenter(this);

		mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
		mCityAdapter = new CityAdapter();
		mRvList.setAdapter(mCityAdapter);
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

	@Override
	public void displayList(List<City> list) {

	}
}
