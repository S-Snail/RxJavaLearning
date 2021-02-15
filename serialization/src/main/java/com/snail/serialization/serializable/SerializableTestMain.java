package com.snail.serialization.serializable;

import android.os.Environment;

class SerializableTestMain {

    public static void main(String[] args) {
        String path = "serialization/tempdir/";
        SerializableUser snail = new SerializableUser("Snail", 18);
        SerializableUtil.saveObject(snail,path + "a.out");
        System.out.println("序列化：" + snail);
        snail = SerializableUtil.readObject(path + "a.out");
        System.out.println("反序列化：" + snail);
    }

}
