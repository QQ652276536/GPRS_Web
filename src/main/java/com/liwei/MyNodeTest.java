package com.liwei;

public class MyNodeTest
{
    public String key;
    public String name;
    public int age;

    public MyNodeTest()
    {
    }

    public MyNodeTest(String key, String name, int age)
    {
        this.key = key;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "NodeTest{" + "key='" + key + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}
