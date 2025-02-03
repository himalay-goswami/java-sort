package dev.himalay.std;

import dev.himalay.util.SortConfiguration;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public interface Sorter {

  // sort in-memory dataset
  <T> List<T> sort(List<T> data, Comparator<T> comparator);

  //sort large dataset from a file
  void fileSort(String inputFilePath, Comparator<String> comparator);

  // sort using streams for real-time processing
  <T> Stream<T> streamSort(Stream<T> data, Comparator<T> comparator);

  //configure advance settings
  default SortConfiguration withConfiguration(SortConfiguration config) {
    return null;
  }
}
