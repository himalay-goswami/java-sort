package main.dev.himalay.std;

import main.dev.himalay.util.SortConfiguration;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SorterImpl implements Sorter{

  @Override
  public <T> List<T> sort(List<T> data, Comparator<T> comparator) {
    return List.of();
  }

  @Override
  public void fileSort(String inputFilePath, Comparator<String> comparator) {

  }

  @Override
  public <T> Stream<T> streamSort(Stream<T> data, Comparator<T> comparator) {
    return Stream.empty();
  }
}
