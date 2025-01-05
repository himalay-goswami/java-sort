package dev.himalay.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface InputReader {

  //read data from file and split into chunks
  List<Path> splitIntoChunks(Path inputFilePath) throws IOException;

  //read data from in-memory list and split into chunks
  List<List<String>> splitIntoChunks(List<String> inputData);

  // Read from any custom data source via an InputStream
  List<Path> splitIntoChunks(InputStream inputStream);

  default Path writeChunkToTempFile(List<String> chunk){
    return Path.of("");
  }
}
