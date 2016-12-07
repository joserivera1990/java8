package com.josecode.java8.stream;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.joining;

import static java.util.Comparator.comparing;

public class ExampleTrader {

	public static void main(String[] args) {
		List<Transaction> transactions = createTransactions();
		
		System.out.println(sortByValue(transactions));
		System.out.println(uniqueCities(transactions));
		System.out.println(uniqueCitiesWithSet(transactions));
		System.out.println(uniqueCitiesWithSetOrder(transactions));
		System.out.println(sortByName(transactions));
		System.out.println(allTradersWithConcatenateNames(transactions));
		System.out.println(allTradersWithConcatenateNamesJoining(transactions));
		System.out.println(checkTraderBasedFromMilan(transactions));
		printAllTransactions(transactions);
		highestValue(transactions);
		smallestValue(transactions);
		minimunValue(transactions);
	}
    
	
	private static List<Transaction> sortByValue(List<Transaction> transactions) {
		return transactions.stream()
						   .filter(transaction -> transaction.getYear() == 2011)
						   .sorted(comparing(Transaction::getValue))
						   .collect(toList());
	}
	
	private static List<String> uniqueCities(List<Transaction> transactions) {
		return transactions.stream()
				           .map(transaction -> transaction.getTrader().getCity())
				           .distinct()
				           .collect(toList());
	} 
	
	private static Set<String> uniqueCitiesWithSet(List<Transaction> transactions) {
		return transactions.stream()
				           .map(transaction -> transaction.getTrader().getCity())
				           .collect(toSet());
	} 
	
	private static Set<String> uniqueCitiesWithSetOrder(List<Transaction> transactions) {
		return transactions.stream()
				           .map(transaction -> transaction.getTrader().getCity())
				           .collect(Collectors.toCollection(LinkedHashSet::new));
	} 
	
	private static List<Trader> sortByName(List<Transaction> transactions) {
		return transactions.stream()
				           .map(Transaction::getTrader)
				           .filter(trader -> trader.getCity().equals("Cambridge"))
				           .distinct()
				           .sorted(comparing(Trader::getName))
				           .collect(toList());
	}
	
	private static String allTradersWithConcatenateNames(List<Transaction> transactions) {
		return transactions.stream()
				           .map(transaction -> transaction.getTrader().getName())
				           .distinct()
				           .sorted()
				           .reduce("", (n1,n2) -> n1 + n2);
	}
	
	private static String allTradersWithConcatenateNamesJoining(List<Transaction> transactions) {
		return transactions.stream()
				           .map(transaction -> transaction.getTrader().getName())
				           .distinct()
				           .sorted()
				           .collect(joining());
	} 
	
	private static boolean checkTraderBasedFromMilan(List<Transaction> transactions) {
		return transactions.stream()
				            .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
		
	}
	
	private static void printAllTransactions(List<Transaction> transactions) {
		transactions.stream()
				           .filter(t -> t.getTrader().getCity().equals("Cambridge"))
				           .map(Transaction::getValue)
				           .forEach(System.out::println);
	}
	
	private static void highestValue(List<Transaction> transactions) {
		Optional<Integer> highestValue = transactions.stream()
				                                     .map(Transaction::getValue)
				                                     .reduce(Integer::max);
		System.out.println(highestValue.get());
		
	}
	
	private static void smallestValue(List<Transaction> transactions) {
		Optional<Transaction> smallestValue = transactions.stream()
				            .reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2); 
		System.out.println(smallestValue.get());
	}
	
	private static void minimunValue(List<Transaction> transactions) {
		Optional<Transaction> smallestValue = transactions.stream()
				                                     .min(comparing(Transaction::getValue));
		System.out.println(smallestValue.get());
	}
	
	private static List<Transaction> createTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
	
}
