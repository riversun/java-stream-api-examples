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
}
