package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Numbers {
    private static Random rnd=new Random();
    private static final List<String> numbers;
    private static List<String> letters;
    private static String pattern="NNLL";

    private static Set<String> numberCar=new HashSet<>();

    static {
        numbers= IntStream.rangeClosed(8,9)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
        letters=IntStream.range(8,26)
                .map(i->i+65)
                .mapToObj(i-> Character.toString((char)i))
                .collect(Collectors.toList());
    }

    private static String makePlateNumber(){
        StringBuilder sb = new StringBuilder();
        for (String s:pattern.split("")) {
            String makePart = makePart(s);
            sb.append(makePart);
        }
        return sb.toString();
    }

    private static String makePart(String patternElement){
        switch (patternElement){
            case "N":
                return numbers.get(rnd.nextInt(numbers.size()));
            case "L":
                return letters.get(rnd.nextInt(letters.size()));
            default:
                return patternElement;
        }
    }

    static String number(){
        while (true){
            String numbers=makePlateNumber();
            if (!numberCar.contains(numbers)){
                numberCar.add(numbers);
                return numbers;
            }
        }
    }

}
