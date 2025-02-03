package dev.himalay.input;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<String> data = new ArrayList<>();
    for (int i = 0; i < 1_000_000; i++) {
      data.add("Row " + i);
    }
  }
}

