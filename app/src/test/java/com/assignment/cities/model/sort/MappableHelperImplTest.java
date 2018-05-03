package com.assignment.cities.model.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur Kasprzak on 02.05.2018.
 */
public class MappableHelperImplTest {

	private MappableHelperImpl<Text> mMappableHelper;
	private List<Text> mTexts;
	private Text mFirst;
	private Text mMiddle;
	private Text mLast;
	private String mQuery;

	@Before
	public void setUp() {
		mMappableHelper = new MappableHelperImpl<>();

		mQuery = "a";

		mTexts = new ArrayList<>();
		mLast = new Text("zz");
		mTexts.add(mLast);
		mFirst = new Text(mQuery.concat("a"));
		mTexts.add(mFirst);
		mMiddle = new Text(mQuery.concat("b"));
		mTexts.add(mMiddle);

		mMappableHelper.parseItems(mTexts, false);
	}

	@Test
	public void getItemsEmptyQuery() {
		List<Text> result = mMappableHelper.getItems();

		Assert.assertEquals(mTexts.size(), result.size());
		Assert.assertEquals(mFirst, result.get(0));
		Assert.assertEquals(mLast, result.get(result.size() - 1));
	}

	@Test
	public void getItemsQuery() {

		List<Text> result = mMappableHelper.getItems(mQuery);

		Assert.assertEquals(2, result.size());
		Assert.assertEquals(mFirst, result.get(0));
		Assert.assertEquals(mMiddle, result.get(1));
	}

	@Test
	public void getItemsQueryInvalid() {

		List<Text> result = mMappableHelper.getItems("d");

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void getNextKey() {

		String nextKey = mMappableHelper.getNextKey("a");
		Assert.assertEquals("b", nextKey);

		String nextKey2 = mMappableHelper.getNextKey("abc");
		Assert.assertEquals("abd", nextKey2);
	}
}
