package icu.fordring.distributedServerIO.fileDisribution;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 读取一个文件的一部分，读取的范围是[start,end)。
 * 输出后的结果为(byte)：
 */
public class FileBlockSenter implements Runnable {
    private long start;
    private long end;
    private long totalSize;
    private FileInfo file;
    private OutputStream out;
    private byte[] buff;
    private int buffSize=1024*1024;
    private CyclicBarrier cb = null;
    private boolean barrierOn=false;
    private int index;

    public FileBlockSenter(long start, long totalSize, FileInfo file, OutputStream out, int index) {
        this.start = start;
        this.totalSize=totalSize;
        this.end = start+totalSize;
        this.file = file;
        this.out = out;
    }
    public  void setBarrier(CyclicBarrier cb){
        this.cb=cb;
        this.barrierOn=true;
    }
    public boolean isBarrierOn(){return barrierOn;}
    public long getTotalSize() {
        return totalSize;
    }

    public FileInfo getFile() {
        return file;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
        this.totalSize=end-start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
        this.totalSize=end-start;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    public int getBuffSize() {
        return buffSize;
    }

    public void setBuffSize(int buffSize) {
        this.buffSize = buffSize;
    }

    /**
     * 读取该文件块所指的数据并向当前输出流输出.
     */
    @Override
    public void run() {
        try {
            MappedByteBuffer in = file.openChannel(start,totalSize, FileChannel.MapMode.READ_ONLY);
//            System.out.println("    --start:"+start+"  size:"+totalSize);
            buff = new byte[buffSize];
            long t = totalSize/buffSize;

            for(int i=0;i<t;i++){
                in.get(buff);
                out.write(buff);
            }
            int y=(int)(totalSize%buffSize);
            if(y>0){
                in.get(buff,0,y);
                out.write(buff,0,y);
            }
            out.flush();
//            out.close();
//            for(long i=0;i<totalSize;i++){
//                byte b=in.get();
//                out.write(0+b);
//            }
//            out.flush();

            if(barrierOn&&cb!=null){
                cb.await();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
