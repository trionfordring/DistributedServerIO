package icu.fordring.distributedServerIO.fileDisribution;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * 封装一个file的信息
 */
public class FileInfo {
    private Path path;
    public long getSize() throws IOException { return Files.size(path); }
    public MappedByteBuffer openChannel(long start,long size,FileChannel.MapMode mode) throws IOException {
        MappedByteBuffer buf = FileChannel.open(path).map(mode,start,size);
        return buf;
    }
    private FileInfo(Path path){
        this.path=path;
    }
    public static class Builder{
        public static FileInfo open(String path){
            return new FileInfo(Paths.get(path));
        }
    }
}
