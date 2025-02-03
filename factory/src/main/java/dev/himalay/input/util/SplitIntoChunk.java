package dev.himalay.input.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SplitIntoChunk {

  private final long chunkSizeInBytes;

  public SplitIntoChunk(long chunkSizeInBytes) {
    this.chunkSizeInBytes = chunkSizeInBytes;
  }

  public static FileWriter fileWriterFactory(){
    return new FileWriter();
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
          Path tempFile = fileWriterFactory().writeChunkToTempFile(chunk);
          chunkFiles.add(tempFile);

          chunk.clear();
          currentChunkSize = 0;
        }
      }
      if(!chunk.isEmpty()){
        Path tempFile = fileWriterFactory().writeChunkToTempFile(chunk);
        chunkFiles.add(tempFile);
      }
    }
    return chunkFiles;
  }
}
