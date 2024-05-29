package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Kata {
    public static String firstNonRepeatingLetter(String s){
        // Stream for counting - contains lowercase and uppercases (and another character, except white spaces)
        List<String> streamOfS = Stream.of(s.toLowerCase().split("")).collect(Collectors.toList());

        // Stream for writing out value for the result
        List<String> streamOfSWithLowerAndUpperCases = Stream.of(s.split("")).collect(Collectors.toList());

        // Map with quantity of incidence
        Map<String, Long> elementCount = streamOfS.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        // Filter indexes by elementCount.get(e) == 1 condition
        List<Integer> indexes = IntStream.range(0, streamOfS.size())
                .filter(i -> elementCount.get(streamOfS.get(i)) == 1)
                .boxed()
                .collect(Collectors.toList());

        String result = "";
        if (!indexes.isEmpty()) { // If List<Integer> indexes hold some values, get first of them
            result = streamOfSWithLowerAndUpperCases.get(indexes.get(0));
        }

        return result;

    }
}


/*
TESTING

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    @Test @DisplayName("Sample tests")
    void sampleTests() {
      assertEquals("a", Kata.firstNonRepeatingLetter("a"), "For input \"a\"");
      assertEquals("t", Kata.firstNonRepeatingLetter("streSS"), "For input \"streSS\"");
      assertEquals("-", Kata.firstNonRepeatingLetter("moon-men"), "For input \"moon-men\"");
      assertEquals("", Kata.firstNonRepeatingLetter("moonmoon"), "For input \"moonmoon\"");
    }
}
 */