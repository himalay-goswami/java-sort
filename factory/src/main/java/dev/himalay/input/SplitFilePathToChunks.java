package dev.himalay.input;

import dev.himalay.input.util.SplitIntoChunk;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
public class SplitFilePathToChunks implements Splitter<Path> {

  private long chunkSizeInBytes;
  private Path tempDirectory;

  @Override
  public List<Path> splitIntoChunks(Path inputFilePath) throws IOException {
    SplitIntoChunk chunk = new SplitIntoChunk(40*1024*1024);
    return chunk.splitFileIntoChunks(inputFilePath);
  }

  @Override
  public Path writeChunkToTempFile(List<String> chunk) {
    return Splitter.super.writeChunkToTempFile(chunk);
  }
}
