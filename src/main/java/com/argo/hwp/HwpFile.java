package com.argo.hwp;

public class HwpFile {
	private boolean valid;
	private String text;
	private String error;
	private HwpHeader header;
	private HwpSummary summary;

	public HwpFile() {
		this.valid = true;
		header = new HwpHeader();
		summary = new HwpSummary();
	}
	
	public void header(boolean compressed, boolean encrypted) {
		header = new HwpHeader();
		header.compressed = compressed;
		header.encrypted = encrypted;
	}
	
	public void summary(String title, String subject, String author, String keywords, String comments, String createDateTime) {
		summary = new HwpSummary();
		summary.title = title;
		summary.subject = subject;
		summary.author = author;
		summary.keywords = keywords;
		summary.comments = comments;
		summary.createDateTime = createDateTime;	
	}
	
	public void error(String message) {
		this.valid = false;
		this.error = message;
	}
	
	public boolean valid() {
		return valid;
	}
	
	public String getText() {
		return text;
	}
	
	public void text(String text) {
		this.text = text;
	}
	
	public String getError() {
		return error;
	}

	public HwpHeader getHeader() {
		return header;
	}	
	
	public HwpSummary getSummary() {
		return summary;
	}
	
	public class HwpHeader {
		HwpVersion version;
		boolean compressed; // bit 0
		boolean encrypted; // bit 1
		boolean viewtext; // bit 2	
		
		public HwpVersion getVersion() {
			return version;
		}
		public void setVersion(HwpVersion version) {
			this.version = version;
		}
		public boolean isCompressed() {
			return compressed;
		}
		public void setCompressed(boolean compressed) {
			this.compressed = compressed;
		}
		public boolean isEncrypted() {
			return encrypted;
		}
		public void setEncrypted(boolean encrypted) {
			this.encrypted = encrypted;
		}
		public boolean isViewtext() {
			return viewtext;
		}
		public void setViewtext(boolean viewtext) {
			this.viewtext = viewtext;
		}
	}
	
	public class HwpVersion {
		int m;
		int n;
		int p;
		int r;

		public String toString() {
			return String.format("%d.%d.%d.%d", m, n, p, r);
		}

		public HwpVersion parseVersion(long longVersion) {
			HwpVersion version = new HwpVersion();
			version.m = (int) ((longVersion & 0xFF000000L) >> 24);
			version.n = (int) ((longVersion & 0x00FF0000L) >> 16);
			version.p = (int) ((longVersion & 0x0000FF00L) >> 8);
			version.r = (int) ((longVersion & 0x000000FFL));
			return version;
		}
	}	

	public class HwpSummary {
		String title;
	    String subject;
	    String author;
	    String keywords;
	    String comments;
	    String createDateTime;	
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getKeywords() {
			return keywords;
		}
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public String getCreateDateTime() {
			return createDateTime;
		}
		public void setCreateDateTime(String createDateTime) {
			this.createDateTime = createDateTime;
		}
	}
}
