package dev.himalay.input;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Splitter<T> {

  List<T> splitIntoChunks(T inputFilePath) throws IOException;

 // List<List<String>> splitIntoChunks(List<String> inputData);

 //  List<Path> splitIntoChunks(InputStream inputStream);

  default Path writeChunkToTempFile(List<String> chunk){
    return Path.of("");
  }
}
