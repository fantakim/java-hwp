package com.argo.hwp;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.argo.hwp.v3.HwpTextExtractorV3;

public class TestHwpReader {
	@Test
	public void testHwpV3Reader() throws IOException {
		ArrayList<File> files = new ArrayList<File>();
		files.add(getResourceFile("v3/4e00-62ff.hwp"));
		files.add(getResourceFile("v3/empty-v3.hwp"));
		files.add(getResourceFile("v3/han_special_char_3.0.hwp"));
		
		for(File file : files) {
			InputStream stream = new FileInputStream(file);
			HwpFile hwp = HwpReader.fromV3(stream);
			
			if(hwp.valid()){
				String text = hwp.getText();
				System.out.println(text);
			}
			
			assertTrue(hwp.valid());
		}
	}
	
	@Test
	public void testHwpV3ExtractText() throws IOException {
		ArrayList<File> files = new ArrayList<File>();
		files.add(getResourceFile("v3/4e00-62ff.hwp"));
		files.add(getResourceFile("v3/empty-v3.hwp"));
		files.add(getResourceFile("v3/han_special_char_3.0.hwp"));
		
		for(File file : files) {
			StringWriter writer = new StringWriter();
			boolean success = HwpTextExtractorV3.extractText(file, writer);
			if(success){
				String text = writer.toString();
				System.out.println(text);
			}
			
			assertTrue(success);
		}
	}
	
	@Test
	public void testHwpV5Reader() throws FileNotFoundException {
		ArrayList<File> files = new ArrayList<File>();
		files.add(getResourceFile("v5/han_grammar.hwp"));
		files.add(getResourceFile("v5/han_special_char.hwp"));
		files.add(getResourceFile("v5/test-distribute.hwp"));
		
		for(File file : files) {
			InputStream stream = new FileInputStream(file);
			HwpFile hwp = HwpReader.fromV5(stream);
			
			if(hwp.valid()){
				String text = hwp.getText();
				System.out.println(text);
			}
			
			assertTrue(hwp.valid());
		}
	}	
	
	@Test
	public void testLargeHwpFileRead() throws FileNotFoundException, IOException {
		File largefile = new File("D:\\Documents\\hwp\\v5\\han_grammar.hwp");
		HwpFile hwpFile = HwpReader.from(largefile);
		
		if(hwpFile.valid()){
			String text = hwpFile.getText();
			System.out.println(text);
		}
	}
	
	private File getResourceFile(String path) {
		return new File(getClass().getResource("/" + path).getFile());
	}
}
