package com.argo.hwp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
		
		// TODO: 파일헤더로 정확한 버전 파악 필요
		HwpFile hwp = HwpTextExtractorV5.extract(source);
		boolean encryptedHwpV5 = hwp.getHeader().isEncrypted();
		
		if (!hwp.valid() && !encryptedHwpV5) {
			hwp = HwpTextExtractorV3.extract(source);
		}
		
		return hwp;
	}
}