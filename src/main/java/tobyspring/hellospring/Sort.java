package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<Integer> scores = Arrays.asList(5, 7, 1, 9, 2, 8, 8);
        Collections.sort(scores);

        scores.forEach(System.out::println);
    }
}
