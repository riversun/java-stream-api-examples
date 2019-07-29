package com.example.withstream;

import java.lang.reflect.Array;
import java.util.Arrays;

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
public class ConcatMergeArrayOfGeneric {

    public static void main(String[] args) {

        String[] strs1 = { "Mike", "John", "Jack" };
        String[] strs2 = { "Ben", "Tom" };

        String[] concatStrs = concat(strs1, strs2);

        System.out.println("concatStrs=" + Arrays.toString(concatStrs));

        Person[] persons1 = {
                new Person("Mike", 15),
                new Person("Tom", 20),

        };

        Person[] persons2 = {

                new Person("John", 23),
                new Person("Ken", 28)
        };

        Person[] concatPersons = concat(persons1, persons2);
        System.out.println("concatPersons=" + Arrays.toString(concatPersons));

    }

    /**
     * Concat arrays
     * 
     * @param a1
     * @param a2
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(final T[] a1, final T... a2) {
        int a1len = a1.length;
        int a2len = a2.length;
        final T[] a3 = (T[]) Array.newInstance(a1.getClass().getComponentType(), a1len + a2len);
        System.arraycopy(a1, 0, a3, 0, a1len);
        System.arraycopy(a2, 0, a3, a1len, a2len);
        return a3;
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
