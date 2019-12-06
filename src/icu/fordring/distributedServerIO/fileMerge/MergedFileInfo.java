package icu.fordring.distributedServerIO.fileMerge;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MergedFileInfo {
    private Path path;
    public MergedFileInfo(String url){
        this.path = Paths.get(url);
    }
    public Path getPath() {
        return path;
    }

}
