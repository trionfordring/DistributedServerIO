package icu.fordring.distributedServerIO.utils;

import icu.fordring.distributedServerIO.utils.TestUtils;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, BrokenBarrierException {
        TestUtils.testAll("C:\\Users\\fordring\\Desktop\\data.txt","C:\\Users\\fordring\\Desktop\\out",128);
        //TestUtils.creatNumDataFile("C:\\Users\\fordring\\Desktop\\data.txt",(long)1e8);
    }
}
