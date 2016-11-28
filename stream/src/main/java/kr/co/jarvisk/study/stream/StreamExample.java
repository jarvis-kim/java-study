package kr.co.jarvisk.study.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamExample {

    public static final void main(String[] args) {
        reduce();
    }

    public static void reduce() {
        List<String> list = Arrays.asList("java", "php", "python", "perl", "c", "lisp", "c#");

        int result = list
                .stream()
                .map(s -> s.length())
                .mapToInt(Integer::new)
                .min().getAsInt();

        System.out.println("sum : " + result);

        Optional<Integer> sum = list
                .stream()
                .map(s -> s.length())
                .reduce((x, y) -> x < y ? x : y);

        sum.ifPresent(System.out::println);

    }
}
