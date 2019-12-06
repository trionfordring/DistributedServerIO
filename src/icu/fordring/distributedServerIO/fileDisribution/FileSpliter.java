package icu.fordring.distributedServerIO.fileDisribution;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CyclicBarrier;

public class FileSpliter {
    public static FileBlockReader[] splitFile(FileInfo file, OutputStream[] targets) throws IOException {
        long persize = file.getSize()/targets.length;
        long yu = file.getSize()%targets.length;
        FileBlockReader group[] = new FileBlockReader[targets.length];
        long index=0;
        for(int i=0;i<group.length-1;i++){
            group[i]=new FileBlockReader(index,persize,file,targets[i],i);
            index+=persize;
        }
        group[group.length-1]=new FileBlockReader(index,persize+yu,file,targets[targets.length-1],targets.length-1);
        return group;
    }
    public static FileBlockReader[] splitFile(FileInfo file, OutputStream[] targets, CyclicBarrier cb) throws IOException{
        FileBlockReader g[] = splitFile(file,targets);
        for(FileBlockReader reader:g)reader.setBarrier(cb);
        return g;
    }
}
