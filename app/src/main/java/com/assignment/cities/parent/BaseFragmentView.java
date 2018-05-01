package com.assignment.cities.parent;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public interface BaseFragmentView {
	Activity getActivity();

	Context getContext();

	void showToast(int msgResId);

	void showToast(String message);

	void showFragmentSnackbar(int msgResId);

	void showFragmentSnackbar(String msg);

	void showFragmentSnackbar(int msgResId, int actionMsgResId,
			View.OnClickListener actionCallback);

	void showFragmentSnackbar(String msg, String actionMsg, View.OnClickListener actionCallback);

	void showSnackbar(int msgResId);

	void showSnackbar(String msg);

	void showSnackbar(int msgResId, int actionMsgResId, View.OnClickListener actionCallback);

	void showSnackbar(String msg, String actionMsg, View.OnClickListener actionCallback);
}
