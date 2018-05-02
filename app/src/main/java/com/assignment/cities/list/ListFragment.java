package com.assignment.cities.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.assignment.cities.MainApplication;
import com.assignment.cities.R;
import com.assignment.cities.list.adapter.CityAdapter;
import com.assignment.cities.model.CitiesRepository;
import com.assignment.cities.model.City;
import com.assignment.cities.model.sort.MappableHelperImpl;
import com.assignment.cities.parent.BaseFragment;

import java.util.List;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class ListFragment extends BaseFragment implements ListContract.View {

	private static final String TAG = ListFragment.class.getSimpleName();

	private RecyclerView mRvList;
	private ProgressBar mProgressBar;
	private AppCompatEditText mEtQuery;

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
		mEtQuery = view.findViewById(R.id.acetQuery);
		mRvList = view.findViewById(R.id.rvList);
		mProgressBar = view.findViewById(R.id.progressBar);
		mProgressBar.setIndeterminate(true);

		mEtQuery.addTextChangedListener(getQueryTextWatcher());
		return view;
	}

	private TextWatcher getQueryTextWatcher() {
		return new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mPresenter.setQuery(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		};
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		CitiesRepository citiesRepository =
				((MainApplication) getActivity().getApplication()).getCitiesRepository();
		MappableHelperImpl<City> cityHelper =
				((MainApplication) getActivity().getApplication()).getCitiesHelper();

		mPresenter = new ListPresenter(this, citiesRepository, cityHelper);

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
	public void setProgressBarVisibility(boolean visible) {
		Log.d(TAG, "setProgressBarVisibility: " + visible);
		mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	@Override
	public void displayList(List<City> list) {
		mCityAdapter.setCities(list);
	}

	@Override
	public boolean isUnavailable() {
		return isDetached() || isRemoving();
	}
}
