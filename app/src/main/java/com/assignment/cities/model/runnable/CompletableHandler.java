package com.assignment.cities.model.runnable;

import android.os.Handler;
import android.os.Message;

import com.assignment.cities.model.callback.OnCompleteCallback;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class CompletableHandler extends Handler {

	public static final int WHAT_INIT_COMPLETED = 1;

	private OnCompleteCallback mCallback;

	public CompletableHandler(OnCompleteCallback callback) {
		mCallback = callback;
	}

	@Override
	public void handleMessage(Message msg) {
		if (mCallback == null) {
			return;
		}

		switch (msg.what) {
			case WHAT_INIT_COMPLETED:
				mCallback.completed();
				break;
			default:
				break;
		}
	}

	public static Message getCompleteMessage() {
		Message message = new Message();
		message.what = WHAT_INIT_COMPLETED;
		return message;
	}
}
