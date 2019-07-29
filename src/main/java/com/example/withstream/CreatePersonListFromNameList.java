package com.example.withstream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Create model object from parameter value list
 * 
 * Result:
 * 
 * List<Person> personList =
 * [Person [name=john, age=0], Person [name=mike, age=0]]
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class CreatePersonListFromNameList {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Mike");

        List<Person> personList = names
                .stream()
                .map(name -> new Person().setName(name))
                .collect(Collectors.toList());

        System.out.println(personList);

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
