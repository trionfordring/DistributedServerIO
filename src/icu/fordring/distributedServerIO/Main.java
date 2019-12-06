package icu.fordring.distributedServerIO;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        long stt = System.currentTimeMillis();
        OutputStream fouts[] = new OutputStream[64];
        for(int i=1;i<=fouts.length;i++)fouts[i-1]=  new FileOutputStream("C:\\Users\\86187\\Desktop\\out\\out_"+i+".txt");
        FileInfo fi = FileInfo.Builder.open("C:\\Users\\86187\\Desktop\\testData.txt");
        System.out.println("size:"+fi.getSize());
        FileBlockReader fs[] = FileSpliter.splitFile(fi,fouts);
        for(int i=0;i<fs.length;i++){
            Thread t = new Thread(fs[i]);
            t.start();

        }
        System.out.println("\nWrite Over!Use "+(System.currentTimeMillis()-stt)+" ms");
    }
}
