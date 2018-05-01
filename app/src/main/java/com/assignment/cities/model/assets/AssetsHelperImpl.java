package com.assignment.cities.model.assets;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Artur Kasprzak on 01.05.2018.
 */
public class AssetsHelperImpl implements AssetHelper {

	@Override
	public String retrieveTextAsset(Context context, String path) {
		InputStream inputStream;
		BufferedInputStream bufferedInputStream;
		byte[] byteBuf;

		try {
			inputStream = context.getAssets().open(path);

			if (inputStream == null) {
				return "";
			}
			bufferedInputStream = new BufferedInputStream(inputStream);

			byteBuf = new byte[1024];
			int readCount;
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();

			while ((readCount = bufferedInputStream.read(byteBuf)) != -1) {
				bytesOut.write(byteBuf, 0, readCount);
			}
			bufferedInputStream.close();
			return bytesOut.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
