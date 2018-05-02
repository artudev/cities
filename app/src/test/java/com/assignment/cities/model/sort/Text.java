package com.assignment.cities.model.sort;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class Text implements Mappable {
	private String mText;

	public Text(String text) {
		mText = text;
	}

	public String getText() {
		return mText;
	}

	public void setText(String text) {
		mText = text;
	}

	@Override
	public String getKey() {
		return mText;
	}

	@Override
	public String getUniqueKey() {
		return getKey();
	}
}
