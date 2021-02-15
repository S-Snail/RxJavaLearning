package com.snail.serialization.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtil {

    //序列化
    synchronized public static boolean saveObject(Object obj, String path) {
        if (obj == null) return false;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));//创建序列化对象流
            oos.writeObject(obj);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null)
            {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    //反序列化
    public static <T> T readObject(String path){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            Object object = ois.readObject();
            return (T) object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            if (ois !=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
