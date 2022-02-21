package me.mohammad.filedownloader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import me.mohammad.filedownloader.libraries.DownloadType;
import me.mohammad.filedownloader.libraries.Downloader;
import me.mohammad.filedownloader.managers.ActionManager;
import me.mohammad.filedownloader.managers.download.BufferedDownloader;
import me.mohammad.filedownloader.managers.download.DownloadOperation;
import me.mohammad.filedownloader.managers.download.SocketDownloader;

public class FileDownloader {
	
	private Downloader downloader;
	private ActionManager actionManager;
	private Map<String, Downloader> downloaders;
	private Map<String, DownloadOperation> operations;
	
	public FileDownloader(final DownloadType type) {
		actionManager = new ActionManager();
		operations = new HashMap<>();
		initializeDownloaders();
		downloader = downloaders.get(type.getDownloadManager());
	}
	
	private void initializeDownloaders() {
		downloaders = new HashMap<>();
		downloaders.put("managers/download/buffer", new BufferedDownloader(this));
		downloaders.put("managers/download/socket", new SocketDownloader(this));
	}
	
	public ActionManager getActionManager() {
		return actionManager;
	}
	
	public Map<String, DownloadOperation> getOperations() {
		return operations;
	}
	
	public DownloadOperation getOperation(final String operationName) {
		return operations.get(operationName);
	}
	
	public void removeOperation(final String operationName) {
		if (!(operations.containsKey(operationName)))
			return;
		operations.remove(operationName);
	}
	
	public DownloadOperation addOperation(final String operationName, final String url, final File output) {
		if (operations.containsKey(operationName))
			return getOperation(operationName);
		final DownloadOperation operation = new DownloadOperation(operationName, url, output);
		operations.put(operationName, operation);
		downloader.update();
		return operation;
	}
	
}
