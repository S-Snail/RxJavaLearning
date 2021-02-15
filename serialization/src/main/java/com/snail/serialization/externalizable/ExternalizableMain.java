package com.snail.serialization.externalizable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ExternalizableMain {

    public static void main(String[] args) {
        ExternalableTest();
    }

    public static void ExternalableTest(){
        ExternalUser externalUser = new ExternalUser("Snail",19);
        System.out.println("序列化：" + externalUser);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] userData = null;
        try {
            oos = new ObjectOutputStream(out);
            oos.writeObject(externalUser);
            userData = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(userData));
            externalUser = (ExternalUser) ois.readObject();
            System.out.println("反序列化：" + externalUser);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
