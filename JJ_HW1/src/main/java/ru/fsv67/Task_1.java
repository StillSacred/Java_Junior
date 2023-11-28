package ru.fsv67;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/*
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 */
public class Task_1 {
    private List<Integer> integerList;

    /**
     * Метод создающий коллекцию List<'Integer'> из 1000 чисел  от 1 до 1_000_000
     */
    public void randomNumberList() {
        integerList = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1000000)).limit(1000).toList();
//        System.out.println(integerList);
    }

    /**
     * Метод находит в списке Lists максимальное число и выводит его в консоль
     */
    public void maxNumberList() {
        int maxNumber = integerList.stream().max(Integer::compare).get();
        System.out.println("Максимальное число в списке: " + maxNumber);
    }

    /**
     * Метод суммирования чисел списка по условию и вывода в консоль
     */
    public void sumNumberListByCondition() {
        int sumNumberList = integerList.stream()
                .filter(x -> x > 500000)
                .map(x -> (x * 5) - 150)
                .reduce(0, Integer::sum);
        System.out.println("Сумма чисел по условию (> 500_000 * 5 и вычесть 150): " + sumNumberList);
    }

    /**
     * Метод нахождения количества чисел, квадрат которых меньше 100_000
     */
    public void findNumberOfNumbersByCondition() {
        long quantity = integerList.stream().map(x -> Math.pow(x, 2)).filter((x) -> x < 100000).count();
        System.out.println("Количество чисел квадрат которых меньше 100_000: " + quantity);
    }
}