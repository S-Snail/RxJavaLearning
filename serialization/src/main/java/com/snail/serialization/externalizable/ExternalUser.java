package com.snail.serialization.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ExternalUser implements Externalizable {

    private String username;

    private int age;

    public ExternalUser() {
    }

    public ExternalUser(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(username);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        username = (String) in.readObject();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "ExternalUser{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
