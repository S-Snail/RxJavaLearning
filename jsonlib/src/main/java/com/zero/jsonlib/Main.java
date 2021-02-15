package com.zero.jsonlib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.zero.jsonlib.gson.Course;
import com.zero.jsonlib.gson.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

public class Main {

    private static String json = "{\"id\":1,\"name\":\"Snail\",\"sax\":\"男\",\"age\":18,\"courses\":\"\"}";

    public static void main(String[] args) throws IOException {
//        Student student = new Student();
//        student.setId(1);
//        student.setName("Snail");
//        student.setAge(18);
//        student.setSax("男");
//        student.addCourse(new Course("英语",99f));
//        //生成Json文件
//        String curPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println("当前Path = " + curPath);
//        Gson gson = new Gson();
//        File file = new File(curPath, "createJson.json");
//        FileOutputStream fos = new FileOutputStream(file);
//        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(fos, "utf-8"));
//        gson.toJson(student,
//                new TypeToken<Student>() {
//                }.getType(),
//                jsonWriter);
//        jsonWriter.flush();
//        jsonWriter.close();
//
//        System.out.println("json文件：" + gson.toJson(student));
//
//        //反序列化
//        Student student1 = gson.fromJson(new JsonReader(
//                        new InputStreamReader(
//                                new FileInputStream(file))),
//                new TypeToken<Student>() {
//                }.getType());
//        System.out.println("反序列化：" + student1.toString());

        //需要的是一个数组类型，但实际返回的是""
        gsonErrorTest();
    }

    private static void gsonErrorTest() throws FileNotFoundException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Student.class, new GsonError1Deserializer());
        Gson gson = gsonBuilder
                .create();
        Student student = gson.fromJson(json, new TypeToken<Student>() {
        }.getType());
        System.out.println(student);
    }


    //方式一：让返回null
    public static class GsonError1Deserializer implements JsonDeserializer<Student> {

        @Override
        public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Student student = new Student();

            JsonObject jsonObject = json.getAsJsonObject();

            JsonElement idElement = jsonObject.get("id");
            int id = idElement.getAsInt();
            JsonElement nameElement = jsonObject.get("name");
            String name = nameElement.getAsString();
            JsonElement saxElement = jsonObject.get("sax");
            String sax = saxElement.getAsString();
            JsonElement ageElement = jsonObject.get("age");
            int age = ageElement.getAsInt();
            JsonElement coursesElement = jsonObject.get("courses");
            if (coursesElement.isJsonArray()) {
                Course[] courses = context.deserialize(coursesElement, Course[].class);
                student.setCourses(Arrays.asList(courses));
            } else {
                student.setCourses(null);
            }
            student.setId(id);
            student.setName(name);
            student.setSax(sax);
            student.setAge(age);
            return student;
        }
    }

    //方式二：用Gson()自带的解决方案


}
