package dev.himalay.input.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SplitIntoChunk {

  private final long chunkSizeInBytes;
  private final Path tempDir;

  public SplitIntoChunk(long chunkSizeInBytes, Path tempDir) {
    this.chunkSizeInBytes = chunkSizeInBytes;
    this.tempDir = tempDir;
  }

  public List<Path> splitFileIntoChunks(Path inputFilePath) throws IOException {

    List<Path> chunkFiles = new ArrayList<>();

    try(BufferedReader reader = Files.newBufferedReader(inputFilePath)){

      List<String> chunk  = new ArrayList<>();

      int currentChunkSize = 0;
      String line;

      while((line = reader.readLine()) != null){

        chunk.add(line);
        currentChunkSize +=line.getBytes().length;

        if(currentChunkSize >= chunkSizeInBytes) {
          Path tempFile = writeChunkToTempFile(chunk);
          chunkFiles.add(tempFile);

          chunk.clear();
          currentChunkSize = 0;
        }
      }
      if(!chunk.isEmpty()){
        Path tempFile = writeChunkToTempFile(chunk);
        chunkFiles.add(tempFile);
      }
    }
    return chunkFiles;
  }

  private Path writeChunkToTempFile(List<String> chunk) throws IOException {
    Path tempFilePath = Files.createTempFile(tempDir, "chunk", ".tmp");

    try(BufferedWriter writer = Files.newBufferedWriter(tempFilePath)) {
      for(String line: chunk){
        writer.write(line);
        writer.newLine();
      }
    }
    return tempFilePath;
  }
}
