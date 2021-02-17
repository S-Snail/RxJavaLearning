package com.snail.java_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedTest {

    private final static String path = "java_io/txtdir/bufferedTest.txt";

    private static final byte[] byteArray = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {
        testBufferedOutputStream();
        testBufferedInputStream();
    }

    private static void testBufferedInputStream() {
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //读前10个字母
            for (int i = 0; i < 10; i++) {
                if (bufferedInputStream.available() >= 0){
                    System.out.println(byteToString((byte)bufferedInputStream.read()));
                }
            }

            bufferedInputStream.mark(6);
            bufferedInputStream.skip(10);

            byte[] bytes = new byte[26];
            bufferedInputStream.read(bytes, 0, bytes.length);
            System.out.println(new String(bytes));

            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String byteToString(byte b) {
        byte[] barray = {b};
        return new String(barray);
    }


    private static void testBufferedOutputStream() {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(new File(path)));
            bufferedOutputStream.write(byteArray[0]);
            bufferedOutputStream.write(byteArray, 1, byteArray.length - 1);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
