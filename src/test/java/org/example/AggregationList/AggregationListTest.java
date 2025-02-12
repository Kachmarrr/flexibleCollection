package org.example.AggregationList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AggregationListTest {
    private AggregationList<Transaction> aggregationList;

    @Before
    public void setUp() {
        aggregationList = new AggregationList<>();
        aggregationList.addAll(List.of(
                new Transaction("USA", "Poland", 1100),
                new Transaction("Germany", "France", 500),
                new Transaction("Brazil", "Italy", 1100),
                new Transaction("Canada", "Sweden", 700),
                new Transaction("China", "South Korea", 1500),
                new Transaction("Brazil", "Canada", 700),
                new Transaction("India", "China", 1100),
                new Transaction("Australia", "India", 700),
                new Transaction("Sweden", "Norway", 500),
                new Transaction("USA", "China", 1500)
        ));
    }

    @Test
    public void groupByTest() {

        Map<String, List<Transaction>> map = aggregationList.groupBy(transaction -> transaction.countrySender);

        map.forEach((key, value) -> System.out.println(key + " " + value));

    }

    @Test
    public void mapReduceTest() {

        Map<String, List<Transaction>> map = aggregationList.mapReduce(transaction -> transaction.countrySender, transaction -> transaction.countrySender.equals("USA"));

        map.forEach((key, value) -> System.out.println(key + " " + value));

    }

    @Test
    public void filterTest() {

        aggregationList.filter(transaction -> transaction.amount > 1000);

        aggregationList.forEach(System.out::println);
    }

    @Test
    public void sortTest() {

        //aggregationList.sort(Comparator.comparing(transaction -> -transaction.amount));

        aggregationList.sort(transaction -> -transaction.amount);

        aggregationList.forEach(System.out::println);
    }

    @Test
    public void transformTest() {

       List<String> senders = aggregationList.transform(transaction -> transaction.countrySender);

       senders.forEach(System.out::println);
    }

    @Test
    public void findTest() {

        List<Transaction> findCondition = aggregationList.find(transaction -> transaction.amount > 1000);

        findCondition.forEach(System.out::println);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Transaction{

        private String countrySender;
        private String countryRecipient;
        private Integer amount;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Transaction that = (Transaction) o;
            return Objects.equals(countrySender, that.countrySender) && Objects.equals(countryRecipient, that.countryRecipient) && Objects.equals(amount, that.amount);
        }

        @Override
        public int hashCode() {
            return Objects.hash(countrySender, countryRecipient, amount);
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "countrySender='" + countrySender + '\'' +
                    ", countryRecipient='" + countryRecipient + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }
}