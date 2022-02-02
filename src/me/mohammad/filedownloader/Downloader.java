package me.mohammad.filedownloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Downloader extends Thread {
	
	private FileDownloader fileDownloader;
	private boolean downloading;
	private boolean updated;
	
	protected Downloader(final FileDownloader fileDownloader) {
		this.fileDownloader = fileDownloader;
		downloading = false;
		updated = updateOperations();
		start();
	}
	
	protected boolean isDownloading() {
		return downloading;
	}
	
	protected void update() {
		updated = updateOperations();
	}
	
	protected boolean updateOperations() {
		if (isDownloading())
			return false;
		fileDownloader.getOperations().forEach((url, operation) -> {
			if (!(operation.hasDownloaded()))
				downloadFile(operation);
		});
		return true;
	}
	
	protected File downloadFile(final DownloadOperation operation) {
		try {
			fileDownloader.onStartedDownloading.accept(operation);
			downloading = true;
			final URL urlObject = new URL(operation.getURL());
			final File output = operation.getAbsolutePath();
			if (!(output.exists())) {
				output.getParentFile().mkdirs();
				output.createNewFile();
			}
			final BufferedInputStream bufferedInput = new BufferedInputStream(urlObject.openStream());
			final FileOutputStream fileOutputStream = new FileOutputStream(output);
			final byte dataBuffer[] = new byte[1024];			
			int bytesRead;
			while ((bytesRead = bufferedInput.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
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
			fileDownloader.onFinishedDownloading.accept(operation);
		}
		return operation.getDownloadedFile();
	}
	
}
