import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Comparator.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream;
        stream = persons.stream();
        long count = stream
                .filter(person -> person.getAge() < 18)
                .count();
        // System.out.println("В результате переписи населения выявилось " + count + " несовершеннолетних жителей.");

        stream = persons.stream();
        List<String> milList = stream
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 28)
                .map(Person::getFamily)
                .toList();
   //      System.out.println(milList);

        stream = persons.stream();
        List<String> higherList = stream
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() < 61) ||
                        (person.getSex() == Sex.MAN && person.getAge() < 66))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(higherList);
    }
}