package dev.himalay.input.sort.impl;

import dev.himalay.input.concurrencymanager.MultiThreadSortExecutor;
import dev.himalay.input.sort.Sort;

import java.io.File;

public class FileSort implements Sort<File> {

  private MultiThreadSortExecutor executor;

  public FileSort(MultiThreadSortExecutor executor) {
    this.executor = executor;
  }

  @Override
  public File sort(File inputFile) throws Exception {
    return MultiThreadSortExecutor.sortExecute(inputFile);
  }
}
