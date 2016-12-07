package com.josecode.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        exampleDistinct();
        flatMapAndDistinctWorking();
        mapNotWorking();
        reduceTest();
    }
    
    private static void exampleDistinct() {
    	List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    	numbers.stream()
    		   .filter(i -> i % 2 == 0)
    	       .distinct()
    	       .forEach(System.out::println);
    }
    
    private static void mapNotWorking() {
    	/*String[] arrayOfWords = {"Goodbye", "World"};
    	Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
    	List<String> list = streamOfwords.map(word->word.split(""))
    				 .map(Arrays::stream)
    				 .distinct()
    				 .collect(Collectors.toList());*/
    	
    }
    
    private static void flatMapAndDistinctWorking() {
		String[] arrayOfWords = {"Goodbye", "World"};
		Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
		List<String> list = streamOfwords.map(word->word.split(""))
					 .flatMap(Arrays::stream)
					 .distinct()
					 .collect(Collectors.toList());
		System.out.println(list);
	}
    
    private static void reduceTest() {
    	List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    	int product = numbers.stream().reduce(0, (a, b) -> a + b);
    	System.out.println(product);
    }
    
}
