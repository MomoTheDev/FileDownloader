package me.mohammad.filedownloader.libraries;

import java.io.File;

import me.mohammad.filedownloader.managers.download.DownloadOperation;

public interface Downloader {

	boolean isDownloading();

	void update();

	boolean updateOperations();

	File downloadFile(DownloadOperation operation);
	
}
