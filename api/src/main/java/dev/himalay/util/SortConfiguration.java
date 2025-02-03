package dev.himalay.util;

import java.nio.file.Path;

public class SortConfiguration {

  private long maxMemoryUsage;
  private int chunkSize;
  private boolean isParallelExecuting;
  private Path tempDirectory;

  private SortConfiguration(){
    //
  }

  public long maxMemoryUsage() {
    return maxMemoryUsage;
  }

  public SortConfiguration setMaxMemoryUsage(long maxMemoryUsage) {
    this.maxMemoryUsage = maxMemoryUsage;
    return this;
  }

  public int chunkSize() {
    return chunkSize;
  }

  public SortConfiguration setChunkSize(int chunkSize) {
    this.chunkSize = chunkSize;
    return this;
  }

  public boolean isParallelExecuting() {
    return isParallelExecuting;
  }

  public SortConfiguration setParallelExecuting(boolean parallelExecuting) {
    isParallelExecuting = parallelExecuting;
    return this;
  }

  public Path tempDirectory() {
    return tempDirectory;
  }

  public SortConfiguration setTempDirectory(Path tempDirectory) {
    this.tempDirectory = tempDirectory;
    return this;
  }

  public int getAvailableCores() {
    return Runtime.getRuntime().availableProcessors();
  }
}
