package com.greetings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> myMap = new ConcurrentHashMap<>();
        myMap.put(1, "one");
        myMap.put(2, "two");
        myMap.put(3, "three");
        System.out.println("Hello " + myMap);
    }
}
