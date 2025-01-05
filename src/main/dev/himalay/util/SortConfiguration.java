package main.dev.himalay.util;

import java.nio.file.Path;

public class SortConfiguration {

  private long maxMemoryUsage; // Maximum memory usage in bytes
  private int chunkSize;       // Size of chunks for external sorting
  private boolean isParallelExecuting;    // Enable/disable parallel processing
  private Path tempDirectory;

  private SortConfiguration(){
    // intentionally left blank
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
}
