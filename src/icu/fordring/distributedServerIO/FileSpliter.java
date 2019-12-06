package icu.fordring.distributedServerIO;

import java.io.IOException;
import java.io.OutputStream;

public class FileSpliter {
    public static FileBlockReader[] splitFile(FileInfo file, OutputStream[] targets) throws IOException {
        long persize = file.getSize()/targets.length;
        long yu = file.getSize()%targets.length;
        FileBlockReader group[] = new FileBlockReader[targets.length];
        long index=0;
        for(int i=0;i<group.length-1;i++){
            group[i]=new FileBlockReader(index,persize,file,targets[i]);
            index+=persize;
        }
        group[group.length-1]=new FileBlockReader(index,persize+yu,file,targets[targets.length-1]);
        return group;
    }
}
