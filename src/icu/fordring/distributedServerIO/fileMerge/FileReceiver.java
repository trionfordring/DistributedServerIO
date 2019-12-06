package icu.fordring.distributedServerIO.fileMerge;

import java.io.InputStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FileReceiver {
    public static void receive(MergedFileInfo target, FileBlockInfo fileBlockInfos[], CyclicBarrier cyclicBarrier) throws BrokenBarrierException, InterruptedException {
        FileBlockReceiver receivers[] = new FileBlockReceiver[fileBlockInfos.length];
        for(int i=0;i<receivers.length;i++){
            receivers[i]=new FileBlockReceiver(fileBlockInfos[i],target,cyclicBarrier);
            new Thread(receivers[i]).start();
        }
        cyclicBarrier.await();
    }
    public static void receive(MergedFileInfo target, FileBlockInfo fileBlockInfos[]){
        FileBlockReceiver receivers[] = new FileBlockReceiver[fileBlockInfos.length];
        for(int i=0;i<receivers.length;i++){
            receivers[i]=new FileBlockReceiver(fileBlockInfos[i],target);
            new Thread(receivers[i]).start();
        }
    }
}
