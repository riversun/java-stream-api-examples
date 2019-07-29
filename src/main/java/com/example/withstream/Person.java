package com.example.withstream;

/**
 * Example model
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class Person {

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