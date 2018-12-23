package com.cc;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author : cc
 * @date : 2018-11-15  16:47
 */
public class LL {

    @Test
    public void test(){
        String s = toUpperStr(
                x->x.toUpperCase(),"ll"
        );
        System.out.println(s);
    }

    String toUpperStr(TTT t, String str){
        return t.getValue(str);
    }

    @Test
    public void test2(){
        ArrayList<Person>list = new ArrayList<>();
        list.add(new Person("张三"));
        list.add(new Person("李四"));
        list.add(new Person("王五"));
        for (Person p : list) {
            p.setName("张三2");
            int i = 5;
        }
    }

    public void test3(){
        Supplier<Person> consumer = Person::new;
    }

}
@Data
class Person{

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    private String name;
}