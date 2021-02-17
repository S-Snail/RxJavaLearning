package com.snail.java_io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class BufferedReaderWriter {

    private final static String path = "java_io/txtdir" + File.separator;
    private static final byte[] byteArray = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };
    public static void main(String[] args) {
        File srcTxt = new File(path + "BufferedReader.txt");
        File dscTxt = new File(path + "BufferedWriter.txt");
//        try {
//            BufferedOutputStream srcBufferedOutputStream = new BufferedOutputStream(
//                    new FileOutputStream(dscTxt));
//
//            srcBufferedOutputStream.write(byteArray);
//            srcBufferedOutputStream.flush();
//            srcBufferedOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(srcTxt));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dscTxt));

            char[] chars = new char[1024];
            while (bufferedReader.read(chars) != -1){
                bufferedWriter.write(chars);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
