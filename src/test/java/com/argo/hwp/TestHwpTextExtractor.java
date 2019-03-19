package com.argo.hwp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.junit.Test;

public class TestHwpTextExtractor {
	@Test
	public void testExtract() throws FileNotFoundException, IOException {
		ArrayList<File> files = new ArrayList<File>();
		files.add(getResourceFile("v3/4e00-62ff.hwp"));
		files.add(getResourceFile("v3/han_special_char_3.0.hwp"));
		files.add(getResourceFile("v5/han_grammar.hwp"));
		files.add(getResourceFile("v5/han_special_char.hwp"));
		
		for(File file : files){
			HwpFile hwpFile = new HwpFile(file);
			
			if(hwpFile.readSuccess()){
				String text = hwpFile.getText();
				System.out.println(text);
			}
		}
	}
	
	private File getResourceFile(String path) {
		return new File(getClass().getResource("/" + path).getFile());
	}
}
