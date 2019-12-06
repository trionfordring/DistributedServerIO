package icu.fordring.distributedServerIO.fileMerge;

import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CyclicBarrier;

public class FileBlockReceiver implements Runnable {
    private FileBlockInfo fileBlockInfo;
    private CyclicBarrier cyclicBarrier=null;
    private MergedFileInfo mergedFileInfo;

    public FileBlockReceiver(FileBlockInfo fileBlockInfo,MergedFileInfo mergedFileInfo, CyclicBarrier cyclicBarrier) {
        this.fileBlockInfo = fileBlockInfo;
        this.cyclicBarrier = cyclicBarrier;
        this.mergedFileInfo = mergedFileInfo;
    }

    public FileBlockReceiver(FileBlockInfo fileBlockInfo,MergedFileInfo mergedFileInfo) {
        this.fileBlockInfo = fileBlockInfo;
        this.mergedFileInfo = mergedFileInfo;
    }

    @Override
    public void run() {

    }
}
