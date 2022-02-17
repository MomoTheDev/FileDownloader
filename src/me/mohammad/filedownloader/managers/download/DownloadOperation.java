package me.mohammad.filedownloader.managers.download;

import java.io.File;

public class DownloadOperation {
	
	private String operationName;
	private String url;
	private File output;
	private File downloadedFile;
	
	private boolean downloaded;

	public DownloadOperation(final String operationName, final String url, final File output) {
		this.operationName = operationName;
		this.url = url;
		this.output = output;
		downloaded = false;
	}
	
	public String getOperationName() {
		return operationName;
	}
	
	public String getURL() {
		return url;
	}
	
	public File getDownloadedFile() {
		return downloadedFile;
	}
	
	public File getAbsolutePath() {
		return new File(output, operationName);
	}
	
	public File getOutput() {
		return output;
	}
	
	public boolean hasDownloaded() {
		return downloaded;
	}
	
	protected void setDownloadedFile(final File downloadedFile) {
		this.downloadedFile = downloadedFile;
	}
	
	protected void setDownloaded(final boolean downloaded) {
		this.downloaded = downloaded;
	}
	
}
