package icu.fordring.distributedServerIO.utils;

import icu.fordring.distributedServerIO.fileDisribution.FileBlockReader;
import icu.fordring.distributedServerIO.fileDisribution.FileSpliter;
import icu.fordring.distributedServerIO.fileDisribution.FileInfo;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class TestUtils {
    public static void testAll(String dataPath,String targetDir,int threadNum) throws IOException, InterruptedException, BrokenBarrierException {
        long stt = System.currentTimeMillis();
        OutputStream fouts[] = new OutputStream[threadNum];
        for(int i=1;i<=fouts.length;i++)fouts[i-1]=  new FileOutputStream(targetDir+"\\out_"+i+".txt");
        FileInfo fi = FileInfo.Builder.open(dataPath);
        System.out.println("size:"+fi.getSize());
        CyclicBarrier cb = new CyclicBarrier(threadNum+1);
        FileBlockReader fs[] = FileSpliter.splitFile(fi,fouts,cb);
        for(int i=0;i<fs.length;i++){
            Thread t = new Thread(fs[i]);
            t.start();
        }
        cb.await();
        System.out.println("\nWrite Over!Use "+(System.currentTimeMillis()-stt)+" ms");
    }
    public static void creatNumDataFile(String target,long num) throws IOException {
        long t = System.currentTimeMillis();
        System.out.println("now writing data to "+target);
        FileWriter fw =new FileWriter(target);
        for(int i=1;i<=num;i++)fw.write(" "+i);
        fw.flush();
        fw.close();
        System.out.println("Over! Use "+(System.currentTimeMillis()-t)+" ms");
    }
}
