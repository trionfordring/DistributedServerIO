package icu.fordring.distributedServerIO.fileMerge;

import icu.fordring.distributedServerIO.FileBlock;

import java.io.InputStream;


public class FileBlockInfo {
    private FileBlock block;
    private InputStream inputStream;

    public FileBlockInfo(FileBlock block, InputStream inputStream) {
        this.block = block;
        this.inputStream = inputStream;
    }

    public FileBlock getBlock() {
        return block;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
