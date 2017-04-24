package com.greetings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreetTest {

    @Test
    public void greetingNobody_greetsTheWorld() {
        Greet greet = new Greet();

        assertEquals(
                greet.greeting(),
                "Hello World!"
        );
    }

    @Test
    public void greetingEmptyArray_greetsTheWorld() {
        Greet greet = new Greet();

        assertEquals(
                greet.greeting(new String[0]),
                "Hello World!"
        );
    }

    @Test
    public void greetingOnePerson_greetsThatPerson() {
        Greet greet = new Greet();

        assertEquals(
                greet.greeting("Alice"),
                "Hello Alice!"
        );
    }

    @Test
    public void greetingSeveralPeople_greetsAllOfThem() {
        Greet greet = new Greet();

        assertEquals(
                greet.greeting("Alice", "Bob", "Charlie"),
                "Hello Alice!\nHello Bob!\nHello Charlie!"
        );
    }

    @Test
    public void greetingArrayOfPeople_greetsAllOfThem() {
        Greet greet = new Greet();

        assertEquals(
                greet.greeting(new String[]{"Alice", "Bob", "Charlie"}),
                "Hello Alice!\nHello Bob!\nHello Charlie!"
        );
    }
}
