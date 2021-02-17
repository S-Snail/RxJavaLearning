package com.snail.java_io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

class InputStreamReaderTest {

    private final static String path = "java_io/txtdir/testOutputStreamWriter.txt";
    private final static String text = "Make more money in this year";

    public static void main(String[] args) {
//        testDefaultInputStreamReader();
//        testGBKInputStreamReader();
        testUTF_8InputStreamReader();
    }

    private static void testUTF_8InputStreamReader() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader utfInputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(utfInputStreamReader);
            String line;
            while ((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testGBKInputStreamReader() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedReader gbkBufferedReader = new BufferedReader(
                    new InputStreamReader(fileInputStream, "GBK"));
            String line;
            while ((line = gbkBufferedReader.readLine()) != null){
                System.out.println(line);
            }
            gbkBufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testDefaultInputStreamReader() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
             bufferedReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
