package com.argo.hwp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class HwpFile {
	private boolean canRead;
	private String text;
	private HwpSummary summary; 

	public HwpFile(String path) throws FileNotFoundException, IOException {
		this(new File(path));
	}
	
	public HwpFile(File file) throws FileNotFoundException, IOException{
		Writer writer = new StringWriter();
		boolean success = canRead = HwpTextExtractor.extract(file, writer);
		if (success) {
			text = writer.toString();
			summary = new HwpSummary();	
		}
	}
	
	public boolean canRead() {
		return canRead;
	}
	
	public String getText() {
		return text;
	}
	
	public HwpSummary getSummary() {
		return summary;
	}
}
