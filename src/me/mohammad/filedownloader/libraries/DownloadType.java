package me.mohammad.filedownloader.libraries;

public enum DownloadType {
	
	JAVA_IO("managers/download/buffer"),
	JAVA_NIO("managers/download/socket");
	
	private String downloadManager;
	
	DownloadType(final String downloadManager) {
		this.downloadManager = downloadManager;
	}
	
	public String getDownloadManager() {
		return downloadManager;
	}
	
}
