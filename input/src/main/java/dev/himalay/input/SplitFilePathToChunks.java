package dev.himalay.input;

import dev.himalay.input.util.SplitIntoChunk;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SplitFilePathToChunks implements InputReader<Path>{

  private long chunkSizeInBytes;
  private Path tempDirectory;

  public InputReaderImpl(long chunkSizeInBytes, Path tempDirectory){
    this.chunkSizeInBytes = chunkSizeInBytes;
    this.tempDirectory = tempDirectory;
  }

  @Override
  public List<Path> splitIntoChunks(Path inputFilePath) throws IOException {
    SplitIntoChunk chunk = new SplitIntoChunk(40*1024*1024, null);
    return chunk.splitFileIntoChunks(inputFilePath);
  }

  @Override
  public List<List<String>> splitIntoChunks(List<String> inputData){
    return null;
  }

  @Override
  public List<Path> splitIntoChunks(InputStream inputStream) {
    return null;
  }
}
