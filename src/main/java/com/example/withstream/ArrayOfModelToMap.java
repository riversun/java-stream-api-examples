package com.example.withstream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Convert model array to Map
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class ArrayOfModelToMap {

    public static void main(String[] args) {

        Person[] persons = new Person[] {
                new Person("Mike", 15),
                new Person("Tom", 20),
                new Person("John", 23),
                new Person("Ken", 28)

        };

        // collect to map simple
        Map<String, Integer> map1 = Arrays.stream(persons)
                .collect(Collectors.toMap(
                        person -> person.name,
                        person -> person.age));

        System.out.println("Mike is " + map1.get("Mike") + " years old");

        // collect to map with map overwritten rule and output class
        LinkedHashMap<String, Integer> map2 = Arrays.stream(persons)
                .collect(Collectors.toMap(
                        person -> person.name,
                        person -> person.age,
                        (oldVal, newVal) -> newVal,
                        LinkedHashMap::new));

        System.out.println("Tom is " + map2.get("Tom") + " years old");

        // extract collector like a function
        Collector<Person, ?, LinkedHashMap<String, Integer>> func = //
                Collectors.toMap(
                        person -> person.name,
                        person -> person.age,
                        (oldVal, newVal) -> newVal,
                        LinkedHashMap::new);

        LinkedHashMap<String, Integer> map3 = Arrays.stream(persons)
                .collect(func);
        System.out.println("John is " + map3.get("John") + " years old");
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
