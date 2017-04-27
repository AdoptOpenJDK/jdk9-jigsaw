package com.greetings;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Greet {

    String greeting(String... names) {
        if ((names == null) || (names.length == 0)) {
            return "Hello World!";
        } else {
            return Arrays.stream(names)
                    .map(name -> String.format("Hello %s!", name))
                    .collect(Collectors.joining("\n"));
        }
    }

}
