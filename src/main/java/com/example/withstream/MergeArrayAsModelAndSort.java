package com.example.withstream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * Make a model list from multiple array of data with stream api.
 * Sort list by public field in a model.
 * 
 * Result:
 * 
 * [
 * Person [name=Dan, age=38], Person [name=Mike, age=25],
 * Person [name=Tom, age=20], Person [name=John, age=15],
 * Person [name=Jude, age=10
 * ]
 * ]
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class MergeArrayAsModelAndSort {

    public static void main(String[] args) {

        String[] names = { "Tom", "Mike", "John", "Jude", "Dan" };
        int[] ages = { 20, 25, 15, 10, 38 };

        // Create a list of model objects
        List<Person> personList = IntStream
                .range(0, names.length)
                .mapToObj(idx -> new Person(names[idx], ages[idx]))
                .collect(Collectors.toList());

        // Sort by public field of model object
        List<Person> sortedPersonList = personList.stream()
                .sorted(Comparator.comparingInt((Person s) -> s.age).reversed())
                .collect(Collectors.toList());

        System.out.println(sortedPersonList);

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
