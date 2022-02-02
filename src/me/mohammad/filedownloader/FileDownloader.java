package me.mohammad.filedownloader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FileDownloader {
	
	private Downloader downloader;
	private Map<String, DownloadOperation> operations;
	
	protected Consumer<DownloadOperation> onStartedDownloading;
	protected Consumer<DownloadOperation> onFinishedDownloading;
	
	public FileDownloader() {
		setOnStartedDownloading((operation) -> {});
		setOnFinishedDownloading((operation) -> {});
		operations = new HashMap<>();
		downloader = new Downloader(this);
	}
	
	protected Map<String, DownloadOperation> getOperations() {
		return operations;
	}
	
	public void setOnStartedDownloading(final Consumer<DownloadOperation> handler) {
		onStartedDownloading = handler;
	}
	
	public void setOnFinishedDownloading(final Consumer<DownloadOperation> handler) {
		onFinishedDownloading = handler;
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
