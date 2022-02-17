package me.mohammad.filedownloader.managers.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import me.mohammad.filedownloader.FileDownloader;
import me.mohammad.filedownloader.libraries.Downloader;

public class SocketDownloader extends Thread implements Downloader {
	
	private FileDownloader fileDownloader;
	private boolean downloading;
	private boolean updated;
	
	public SocketDownloader(final FileDownloader fileDownloader) {
		this.fileDownloader = fileDownloader;
		downloading = false;
		updated = updateOperations();
		start();
	}
	
	@Override
	public boolean isDownloading() {
		return downloading;
	}
	
	@Override
	public void update() {
		updated = updateOperations();
	}
	
	@Override
	public boolean updateOperations() {
		if (isDownloading())
			return false;
		fileDownloader.getOperations().forEach((url, operation) -> {
			if (!(operation.hasDownloaded()))
				downloadFile(operation);
		});
		return true;
	}
	
	public File downloadFile(final DownloadOperation operation) {
		try {
			fileDownloader.getActionManager().acceptStartedDownloading(operation);
			downloading = true;
			final URL urlObject = new URL(operation.getURL());
			final File output = operation.getAbsolutePath();
			if (!(output.exists())) {
				output.getParentFile().mkdirs();
				output.createNewFile();
			}
			final ReadableByteChannel readableByteChannel = Channels.newChannel(urlObject.openStream());
			final FileOutputStream fileOutputStream = new FileOutputStream(output);
			final FileChannel fileChannel = fileOutputStream.getChannel();
			fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
			operation.setDownloadedFile(operation.getAbsolutePath());
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.printf("An error occoured while downloading file: %s\n", operation.getURL());
			e.printStackTrace();
			return null;
		} finally {
			operation.setDownloaded(true);
			downloading = false;
			if (!(updated))
				update();
			fileDownloader.getActionManager().acceptFinishedDownloading(operation);
		}
		return operation.getDownloadedFile();
	}
	
}
