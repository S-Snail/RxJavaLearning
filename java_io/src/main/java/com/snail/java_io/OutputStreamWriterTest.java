package com.snail.java_io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {

    private final static String path = "java_io/txtdir/testOutputStreamWriter.txt";
    private final static String text = "Make more money in this year";
    public static void main(String[] args) {
        testOutputStreamWriter();
    }

    private static void testOutputStreamWriter() {
        File file = new File(path);

        try {
            FileOutputStream fos = new FileOutputStream(file,true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.flush();
//            bufferedWriter.close();
            System.out.println("OutputStreamWriter default encoding is " + outputStreamWriter.getEncoding());

            //GBK
            OutputStreamWriter gbkOutputStreamWriter = new OutputStreamWriter(fos, "GBK");
            BufferedWriter gbkBufferedWriter = new BufferedWriter(gbkOutputStreamWriter);
            gbkBufferedWriter.write(text + " GBK");
            gbkBufferedWriter.newLine();
            gbkBufferedWriter.flush();
//            gbkBufferedWriter.close();
            System.out.println("OutputStreamWriter encoding is " + gbkOutputStreamWriter.getEncoding());

            //UTF-8
            OutputStreamWriter utfOutputStreamWriter = new OutputStreamWriter(fos,"UTF-8");
            BufferedWriter utfBufferedWriter = new BufferedWriter(utfOutputStreamWriter);
            utfBufferedWriter.write(text + " UTF-8");
            utfBufferedWriter.newLine();
            utfBufferedWriter.flush();
//            utfBufferedWriter.close();
            System.out.println("OutputStreamWriter encoding is : " + utfOutputStreamWriter.getEncoding());

            bufferedWriter.close();
            gbkBufferedWriter.close();
            utfBufferedWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
