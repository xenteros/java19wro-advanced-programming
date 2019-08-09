package com.github.xenteros;

import com.github.xenteros.multithreading.Counter;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class Application {

    public static void main(String[] args) {

        Counter one = new Counter("One");
        Counter two = new Counter("Two");

        Thread tOne = new Thread(one);
        Thread tTwo = new Thread(two);

        tOne.setPriority(6);
        tTwo.setPriority(5);

        tOne.start();
        tTwo.start();

        System.out.println(tOne.getPriority());
        System.out.println(tTwo.getPriority());



    }

    private static void experimentWithArrayList(int size) {
        List<Integer> numbers = IntStream.range(0, size)
                .boxed()
                .collect(toList());

        for (int i = size / 2; i < 1.5 * size; i++) {
            boolean contains = numbers.contains(i);
        }
    }

    private static void experimentWithHashSet(int size) {
        Set<Integer> numbers = IntStream.range(0, size)
                .boxed()
                .collect(toSet());

        for (int i = size / 2; i < 1.5 * size; i++) {
            boolean contains = numbers.contains(i);
        }
    }

}
