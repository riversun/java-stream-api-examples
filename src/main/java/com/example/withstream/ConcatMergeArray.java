package com.example.withstream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * Concat Merge arrays
 *
 * Result:
 * 
 * { "Mike", "John", "Jack" } + { "Ben", "Tom" } = {Mike, John, Jack, Ben, Tom}
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class ConcatMergeArray {

    public static void main(String[] args) {

        String[] strs1 = { "Mike", "John", "Jack" };
        String[] strs2 = { "Ben", "Tom" };

        // Merge arrays using flatMap

        String[] result = Stream
                .of(strs1, strs2)
                .flatMap(Stream::of)
                .toArray(String[]::new);

        System.out.println(Arrays.toString(result));

        // Merge arrays using concat
        String[] result2 = Stream
                .concat(Arrays.stream(strs1), Arrays.stream(strs2))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(result2));
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
