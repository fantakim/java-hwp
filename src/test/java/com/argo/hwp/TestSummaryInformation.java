package com.argo.hwp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import org.apache.poi.hpsf.MarkUnsupportedException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.junit.Test;

public class TestSummaryInformation {
	@Test
	public void testGetSummaryInformation() throws IOException, NoPropertySetStreamException, MarkUnsupportedException {
		DocumentInputStream documentInputStream = (DocumentInputStream) null;
		File source = new File("d:\\회의록_20100601_산출물제출일정협의.hwp");
		
		// v3 버전은 진입불가
		NPOIFSFileSystem fs = new NPOIFSFileSystem(source);
		documentInputStream = fs.createDocumentInputStream("HwpSummaryInformation");

		PropertySet propertySet = PropertySetFactory.create((InputStream) documentInputStream);
		documentInputStream.close();

		for (Property property : propertySet.getSections().get(0).getProperties()) {
			long id = property.getID();
			Object value = property.getValue();
			
			System.out.println(value);
			
		}
	}
	
	private File getResourceFile(String path) {
		return new File(getClass().getResource("/" + path).getFile());
	}	
}
