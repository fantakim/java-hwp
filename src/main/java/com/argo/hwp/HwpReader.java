package com.argo.hwp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.argo.hwp.v3.HwpTextExtractorV3;
import com.argo.hwp.v5.HwpTextExtractorV5;

public class HwpReader {
	private HwpReader() {
	}
	
	public static HwpFile from(String path) throws FileNotFoundException, IOException {
		return from(new File(path));
	}
	
	public static HwpFile from(File source) throws FileNotFoundException, IOException {
		if (source == null)
			throw new IllegalArgumentException();
		if (!source.exists())
			throw new FileNotFoundException();
		
		InputStream stream = new FileInputStream(source);
		
		// TODO: 버전 판단해줄것
		return fromV5(stream);
	}
	
	public static HwpFile fromV3(InputStream stream) {
		HwpFile hwp = new HwpFile();
		try {
			hwp = HwpTextExtractorV3.extract(stream);
		} catch (IOException e) {
			hwp.error(e.getMessage());
		}
		
		return hwp;
	}
	
	public static HwpFile fromV5(InputStream stream) {
		HwpFile hwp = new HwpFile();
		try {
			hwp = HwpTextExtractorV5.extract(stream);
		} catch (IOException e) {
			hwp.error(e.getMessage());
		}
		
		return hwp;
	}
}
