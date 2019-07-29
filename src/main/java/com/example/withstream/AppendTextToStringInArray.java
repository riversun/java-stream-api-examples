package com.example.withstream;

import java.util.Arrays;
import java.util.List;

/**
 * Edit string with stream
 * 
 * Result:
 * 
 * [John, Mike]=>[Hi! John, Hi! Mike]
 * 
 * [Jack, Tom]=>[Good mornin!Jack, Good mornin!Tom]
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class AppendTextToStringInArray {

    public static void main(String[] args) {

        // List to Array
        List<String> nameList = Arrays.asList("John", "Mike");

        String[] editedNames = nameList
                .stream()
                .map(name -> new String("Hi! " + name))
                .toArray(String[]::new);

        System.out.println("editedNames=" + Arrays.toString(editedNames));

        // Array to Array
        String[] names = { "Jack", "Tom" };

        String[] editedNames2 = Arrays
                .stream(names)
                .map(name -> new String("Good mornin!" + name))
                .toArray(String[]::new);

        System.out.println("editedNames2=" + Arrays.toString(editedNames2));

    }

    public static class Person {

        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }

        public Person setName(String name) {
            this.name = name;
            return Person.this;
        }

        public Person setAge(int age) {
            this.age = age;
            return Person.this;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }

    }
}
