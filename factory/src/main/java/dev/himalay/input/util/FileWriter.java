package dev.himalay.input.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriter {

  private static final Path tempDir = Path.of("");

  public FileWriter(){
    // do-nothing
  }

  public Path writeChunkToTempFile(List<String> chunkData) throws IOException {
    return writeChunk(chunkData);
  }

  private static Path writeChunk(List<String> chunk) throws IOException {
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
