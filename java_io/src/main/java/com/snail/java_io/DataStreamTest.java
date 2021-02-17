package com.snail.java_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamTest {

    public static void main(String[] args) {
        testDataOutputStream();
        testDataInputStream();
    }

    private static void testDataInputStream() {
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("java_io/txtdir/testDataOutputStream.txt"))));
            System.out.println(dataInputStream.readBoolean());;
            System.out.println(byteToHexString(dataInputStream.readByte()));
            System.out.println(charToHexString(dataInputStream.readChar()));
            System.out.println(shotToHexString(dataInputStream.readShort()));
            System.out.println(Integer.toHexString(dataInputStream.readInt()));
            System.out.println(Long.toHexString(dataInputStream.readLong()));
            System.out.println(dataInputStream.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String shotToHexString(short readShort) {
        return Integer.toHexString(readShort & 0xffff);
    }

    private static String charToHexString(char readChar) {
        return Integer.toHexString(readChar);
    }

    private static String byteToHexString(byte val) {
        return Integer.toHexString(val & 0xff);
    }

    private static void testDataOutputStream() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File("java_io/txtdir/testDataOutputStream.txt"))));
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeByte((byte)0x41);
            dataOutputStream.writeChar((char)0x4243);
            dataOutputStream.writeShort((short)0x4445);
            dataOutputStream.writeInt(0x12345678);
            dataOutputStream.writeLong(0x987654321L);
            dataOutputStream.writeUTF("abcdefg你好");
            dataOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
