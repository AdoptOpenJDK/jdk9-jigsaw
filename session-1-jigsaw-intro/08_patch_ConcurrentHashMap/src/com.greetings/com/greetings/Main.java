package com.greetings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new ConcurrentHashMap<>();
        myMap.put("one", 1);
        myMap.put("two", 2);
        myMap.put("three", 3);
        System.out.println("Hello " + myMap);
        System.out.println("Is Duke home? " + myMap.containsKey("Duke"));
    }
}
