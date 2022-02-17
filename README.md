# FileDownloader
A lightweight file downloader library for Java

# Installation
1. Download the latest file from the releases section
2. Create a project in your favorite Java IDE
3. Add the downloaded file as a library in your project
4. Use and Enjoy!

# Usage
In order to use the library, we have to create an Instance of the `FileDownloader`-Class
Here's how you create an Instance of it:
```java

import me.mohammad.filedownloader.FileDownloader;
import me.mohammad.filedownloader.libraries.DownloadType;

public class Example {

    private FileDownloader fileDownloader;

    public Example() {
        fileDownloader = new FileDownloader(DownloadType.JAVA_IO);
    }
  
}

```

The constructor `new FileDownloader(DownloadType)` takes a `DownloadType` type as a parameter, you can use `DownloadType.JAVA_IO` for buffered downloading and `DownloadType.JAVA_NIO` for socket downloading.

---

With our Instance, we can add operations to the Downloader-Thread by calling the `FileDownloader.addOperation(Name, Url, Output)` method.
Here's a code example:
```java

//... default declarations and etc.

private void downloadFile() {
    fileDownloader.addOperation("Download #1", "Download-Link", new File("C:/Users/Someone/Desktop/"));
}

```

Now, our operation is registered and bypassed to the Downloader-Thread, it'll be put in a map with other download operations, as soon as it get's on the first place, it'll be downloaded to the output directory.

---

We can get the instance of our `DownloadOperation` using it's name.
Here's the code for that:
```java

//... default declarations and etc.

private void getInstance() {
    final DownloadOperation operationInstance = fileDownloader.getOperation("Download #1");
}

```

---

The `DownloadOperation`-Class/Object contains a various amount of methods, that could make the development easier. The file will be saved to the path, that we casted to the `FileDownloader.addOperation(...);` method. We can get the file using `DownloadOperation.getDownloadedFile();`. But remember to check if the file is downloaded (using `DownloadOperation.hasDownloaded()`) before getting the downloaded file.

# Updating
I have enough time to manage this library by my own, you can suggest me some features you'd like or maybe report some bugs as well.
You can give suggestions and report bugs to me via Discord (MomoTheSiM#6478).

# Thanks
You can use this library, the way you'd like.
The only thing you're not allowed to do, is to call it yours, you could atleast give credit to Mohammad Alkhatib (MomoTheSiM) for developing it.
Thanks for using my library, consider supporting me by leaving a star!
