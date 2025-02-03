package dev.himalay.input.concurrencymanager;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MultiThreadSortExecutor {

  private static final int CHUNK_SIZE_MB = 200;
  private static final int THREAD_MULTIPLIER = 2;

  public static void sortExecute() throws Exception {
    File inputFile = new File("largefile.txt");
    File outputFile = new File("sorted_largefile.txt");

    int availableCores = Runtime.getRuntime().availableProcessors();
    int numThreads = availableCores * THREAD_MULTIPLIER;

    List<File> sortedChunks = splitAndSortChunks(inputFile, numThreads);
    mergeSortedChunks(sortedChunks, outputFile);

    System.out.println("Sorting completed!");
  }

  private static List<File> splitAndSortChunks(File inputFile, int numThreads) throws Exception {
    List<File> chunkFiles = new ArrayList<>();
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    BufferedReader reader = new BufferedReader(new FileReader(inputFile));

    String line;
    List<String> buffer = new ArrayList<>();
    long chunkSizeBytes = CHUNK_SIZE_MB * 1024 * 1024;
    long currentSize = 0;

    while ((line = reader.readLine()) != null) {
      buffer.add(line);
      currentSize += line.length();

      if (currentSize >= chunkSizeBytes) {
        chunkFiles.add(sortAndWriteChunk(buffer, executor));
        buffer.clear();
        currentSize = 0;
      }
    }
    if (!buffer.isEmpty()) {
      chunkFiles.add(sortAndWriteChunk(buffer, executor));
    }

    reader.close();
    executor.shutdown();
    boolean isTerminated  = executor.awaitTermination(1, TimeUnit.HOURS);
    System.out.println(isTerminated);
    return chunkFiles;
  }

  private static File sortAndWriteChunk(List<String> buffer, ExecutorService executor) throws Exception {
    Collections.sort(buffer);
    File tempFile = File.createTempFile("sortedChunk", ".txt");

    executor.submit(() -> {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
        for (String line : buffer) {
          writer.write(line);
          writer.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    return tempFile;
  }

  private static void mergeSortedChunks(List<File> chunkFiles, File outputFile) throws Exception {
    PriorityQueue<BufferedReaderWrapper> queue = new PriorityQueue<>();

    for (File chunk : chunkFiles) {
      BufferedReader reader = new BufferedReader(new FileReader(chunk));
      queue.add(new BufferedReaderWrapper(reader));
    }

    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

    while (!queue.isEmpty()) {
      BufferedReaderWrapper smallest = queue.poll();
      writer.write(smallest.currentLine);
      writer.newLine();
      if (smallest.next()) {
        queue.add(smallest);
      } else {
        smallest.reader.close();
      }
    }
    writer.close();
  }

  private static class BufferedReaderWrapper implements Comparable<BufferedReaderWrapper> {
    BufferedReader reader;
    String currentLine;

    public BufferedReaderWrapper(BufferedReader reader) throws IOException {
      this.reader = reader;
      this.currentLine = reader.readLine();
    }

    public boolean next() throws IOException {
      this.currentLine = reader.readLine();
      return this.currentLine != null;
    }

    @Override
    public int compareTo(BufferedReaderWrapper other) {
      return this.currentLine.compareTo(other.currentLine);
    }
  }
}
