package com.snail.java_io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {

    private final static String path = "java_io/txtdir" + File.separator + "ObjectStream.txt";

    private static File newFile(String path){
        File file = new File(path);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void main(String[] args) {
//        testObjectOutputStreamStream();
//        testObjectInputStream();
    }

    private static void testObjectInputStream() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(newFile(path)
                            )
                    ));
            while (objectInputStream.available() != -1){
                Object object = objectInputStream.readObject();
                Person person = (Person) object;
                System.out.println(person);
            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testObjectOutputStreamStream() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(newFile(path)));
            for (int i = 0; i < 10; i++) {
                Person person = new Person("李", i);
                objectOutputStream.writeObject(person);
            }
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
