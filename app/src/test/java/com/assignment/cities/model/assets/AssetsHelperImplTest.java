package com.assignment.cities.model.assets;

import android.content.Context;
import android.content.res.AssetManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class AssetsHelperImplTest {

	@Mock
	Context mContext;
	@Mock
	AssetManager mAssetManager;
	@Mock
	InputStream mInputStream;

	private static final String TEST_STRING = "a";

	private AssetsHelperImpl mAssetsHelper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(mAssetManager).when(mContext).getAssets();

		mInputStream = new ByteArrayInputStream(TEST_STRING.getBytes());
		Mockito.doReturn(mInputStream).when(mAssetManager).open(Mockito.anyString());

		mAssetsHelper = new AssetsHelperImpl();
	}

	@Test
	public void retrieveTextAsset() {

		String value = mAssetsHelper.retrieveTextAsset(mContext, "doesnt_matter_path");

		Assert.assertNotNull(value);
		Assert.assertEquals(TEST_STRING, value);
	}
}
