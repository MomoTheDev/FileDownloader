package me.mohammad.filedownloader.managers;

import java.util.function.Consumer;

import me.mohammad.filedownloader.managers.download.DownloadOperation;

public class ActionManager {
	
	private Consumer<DownloadOperation> onStartedDownloading;
	private Consumer<DownloadOperation> onFinishedDownloading;
	
	public ActionManager() {
		setOnStartedDownloading((operation) -> {});
		setOnFinishedDownloading((operation) -> {});
	}
	
	public void setOnStartedDownloading(final Consumer<DownloadOperation> handler) {
		onStartedDownloading = handler;
	}
	
	public void setOnFinishedDownloading(final Consumer<DownloadOperation> handler) {
		onFinishedDownloading = handler;
	}
	
	public void acceptStartedDownloading(final DownloadOperation operation) {
		onStartedDownloading.accept(operation);
	}
	
	public void acceptFinishedDownloading(final DownloadOperation operation) {
		onFinishedDownloading.accept(operation);
	}
	
}
