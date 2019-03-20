package com.argo.hwp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class HwpFile {
	private boolean valid;
	private String text;
	private String error;
	private HwpHeader header;
	private HwpSummary summary;

	public HwpFile() {
		
	}
	
	public HwpFile(String path) throws FileNotFoundException, IOException {
		this(new File(path));
	}
	
	public HwpFile(File file) throws FileNotFoundException, IOException{
		header = new HwpHeader();
		summary = new HwpSummary();
		
		Writer writer = new StringWriter();
		valid = HwpTextExtractor.extract(file, writer);
		
		if (valid) {
			text = writer.toString();
			summary = new HwpSummary();	
		}
	}
	
	public boolean valid() {
		return valid;
	}
	
	public String getText() {
		return text;
	}
	
	public String getError() {
		return error;
	}

	public HwpHeader header() {
		return header;
	}	
	
	public HwpSummary summary() {
		return summary;
	}
	
	static class HwpHeader {
		HwpVersion version;
		boolean compressed; // bit 0
		boolean encrypted; // bit 1
		boolean viewtext; // bit 2
	}
	
	static class HwpVersion {
		int m;
		int n;
		int p;
		int r;

		public String toString() {
			return String.format("%d.%d.%d.%d", m, n, p, r);
		}

		public static HwpVersion parseVersion(long longVersion) {
			HwpVersion version = new HwpVersion();
			version.m = (int) ((longVersion & 0xFF000000L) >> 24);
			version.n = (int) ((longVersion & 0x00FF0000L) >> 16);
			version.p = (int) ((longVersion & 0x0000FF00L) >> 8);
			version.r = (int) ((longVersion & 0x000000FFL));
			return version;
		}
	}	

	static class HwpSummary {
		String title;
	    String subject;
	    String author;
	    String createtime;
	    String keyword;
	    String comment;	
	}
}
