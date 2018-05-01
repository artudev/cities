package com.assignment.cities.parent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class BaseFragment extends Fragment implements BaseFragmentView {

	private static final String TAG = BaseFragment.class.getSimpleName();

	private ArrayList<Snackbar> mFragmentSnackbars;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (!(context instanceof BaseActivity)) {
			throw new IllegalArgumentException("BaseFragment must be attached to BaseActivity");
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFragmentSnackbars = new ArrayList<>();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
		getBaseActivity().hideKeyboard();
	}

	@Override
	public void onPause() {
		for (Snackbar snackbar : mFragmentSnackbars) {
			snackbar.dismiss();
		}
		super.onPause();
	}

	public BaseActivity getBaseActivity() {
		return (BaseActivity) getActivity();
	}

	@Override
	public void showToast(int msgResId) {
		if (!isAdded()) {
			return;
		}
		showToast(getString(msgResId));
	}

	@Override
	public void showToast(String message) {
		if (!isAdded()) {
			return;
		}
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}

	/**
	 * @see #showFragmentSnackbar(String, String, View.OnClickListener)
	 */
	@Override
	public void showFragmentSnackbar(int msgResId) {
		if (!isAdded()) {
			return;
		}
		showFragmentSnackbar(getString(msgResId));
	}

	/**
	 * @see #showFragmentSnackbar(String, String, View.OnClickListener)
	 */
	@Override
	public void showFragmentSnackbar(String msg) {
		if (!isAdded()) {
			return;
		}
		showFragmentSnackbar(msg, null, null);
	}

	/**
	 * @see #showFragmentSnackbar(String, String, View.OnClickListener)
	 */
	@Override
	public void showFragmentSnackbar(int msgResId, int actionMsgResId,
			View.OnClickListener actionCallback) {
		if (!isAdded()) {
			return;
		}
		showFragmentSnackbar(getString(msgResId), getString(actionMsgResId), actionCallback);
	}

	/**
	 * Shows snackbar that will be dismissed on fragment replacement
	 */
	@Override
	public void showFragmentSnackbar(String msg, String actionMsg,
			View.OnClickListener actionCallback) {
		if (!isAdded()) {
			return;
		}
		Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
		if (actionCallback != null) {
			snackbar.setAction(actionMsg, actionCallback).show();
		}
		snackbar.addCallback(new Snackbar.Callback() {
			@Override
			public void onDismissed(Snackbar snackbar, int event) {
				super.onDismissed(snackbar, event);
				mFragmentSnackbars.remove(snackbar);
			}
		});
		mFragmentSnackbars.add(snackbar);
		snackbar.show();
	}

	/**
	 * @see #showSnackbar(String, String, View.OnClickListener)
	 */
	@Override
	public void showSnackbar(int msgResId) {
		if (!isAdded()) {
			return;
		}
		showSnackbar(getString(msgResId));
	}

	/**
	 * @see #showSnackbar(String, String, View.OnClickListener)
	 */
	@Override
	public void showSnackbar(String msg) {
		if (!isAdded()) {
			return;
		}
		showSnackbar(msg, null, null);
	}

	/**
	 * @see #showSnackbar(String, String, View.OnClickListener)
	 */
	@Override
	public void showSnackbar(int msgResId, int actionMsgResId,
			View.OnClickListener actionCallback) {
		if (!isAdded()) {
			return;
		}
		showSnackbar(getString(msgResId), getString(actionMsgResId), actionCallback);
	}

	/**
	 * Shows snackbar that will live longer then fragment
	 */
	@Override
	public void showSnackbar(String msg, String actionMsg, View.OnClickListener actionCallback) {
		if (!isAdded()) {
			return;
		}
		Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
		if (actionCallback != null) {
			snackbar.setAction(actionMsg, actionCallback).show();
		}
		snackbar.show();
	}
}
