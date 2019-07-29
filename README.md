# Overview
It is licensed under [MIT](https://opensource.org/licenses/MIT).

# Append specific text to String

```java
package com.example.withstream;

import java.util.Arrays;
import java.util.List;

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

}
```

**Execution Result**
```
editedNames=[Hi! John, Hi! Mike]
editedNames2=[Good mornin!Jack, Good mornin!Tom]
```


# Create List<Person> from Strings

```java
package com.example.withstream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
```

**Execution Result**

```
[Person [name=John, age=0], Person [name=Mike, age=0]]
```

# Create Map<String,Person> from Person[]

```java
package com.example.withstream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CreateMapFromPersonArray {

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

```

**Execution Result**

```
Mike is 15 years old
Tom is 20 years old
John is 23 years old

```


# Concat Array (Generic Array)

```java
package com.example.withstream;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ConcatArrays {

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
```

**Execution Result**

```
concatStrs=[Mike, John, Jack, Ben, Tom]
concatPersons=[Person [name=Mike, age=15], Person [name=Tom, age=20], Person [name=John, age=23], Person [name=Ken, age=28]]
```

# Create sorted List<Person> from names and ages array.

```java
package com.example.withstream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateSortedListFromEachArray {

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
```

**Execution Result**

```
[Person [name=Dan, age=38], Person [name=Mike, age=25], Person [name=Tom, age=20], Person [name=John, age=15], Person [name=Jude, age=10]]

```
