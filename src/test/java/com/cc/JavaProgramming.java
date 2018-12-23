package com.cc;

/**
 * @author : cc
 * @date : 2018-12-09  20:07
 */
public class JavaProgramming {
    public static void main(String[] args) {

        Student student = new Student();
        student.age = 55;
        System.out.println(student.age);

        Student test = student.test(student);

        System.out.println(student.age);
        System.out.println(student==test);
    }
}

class Student{
    int age;
    public Student test(Student s){
        s.age=66;
        return s;
    }
}
