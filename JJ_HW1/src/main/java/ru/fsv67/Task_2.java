package ru.fsv67;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списка сотрудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */
public class Task_2 {
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Метод добавления сотрудников в список
     */
    public void addEmployee() {
        employees.add(new Employee("Иван", 30, 10000, "Снабжение"));
        employees.add(new Employee("Ирина", 40, 50000, "Бухгалтерия"));
        employees.add(new Employee("Сергей", 35, 60000, "IT"));
        employees.add(new Employee("Михаил", 28, 60000, "IT"));
        employees.add(new Employee("Николай", 40, 90000, "IT"));
        employees.add(new Employee("Марина", 35, 35000, "Бухгалтерия"));
        employees.add(new Employee("Светлана", 28, 35000, "Бухгалтерия"));
        employees.add(new Employee("Виктор", 25, 40000, "Менеджер"));
        employees.add(new Employee("Артем", 26, 20000, "Менеджер"));
        employees.add(new Employee("Евгений", 30, 34000, "Водитель"));
        employees.add(new Employee("Андрей", 50, 34000, "Водитель"));
        employees.add(new Employee("Вася", 50, 8000, "Дворник"));
//        System.out.println(employees);
    }

    /**
     * Метод вывода уникальных значений по полю department
     */
    public void getVariousDepartments() {
        employees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
    }

    /**
     * Метод повышения заработной платы сотрудника
     */
    public void salaryIncrease() {
        employees.stream().filter(it -> it.getSalary() < 10000).forEach(it -> it.setSalary(it.getSalary() * 1.2));
        employees.stream().filter(it -> it.getSalary() < 10000).forEach(System.out::println);
    }

    /**
     * Метод формирования коллекции Map по отделу
     */
    public void getMapEmployee() {
        Map<String, List<Employee>> mapEmployee =
                employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        mapEmployee.forEach((k, v) -> System.out.println("Отдел: " + k + "\nСотрудники: \n" + v));
    }

    /**
     * Метод формирования коллекции Map средней зарплаты по отделу
     */
    public void getMapSalary() {
        Map<String, Double> mapSalary =
                employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        mapSalary.forEach((k, v) -> System.out.println("Отдел: " + k + "; Средняя зарплата по отделу: " + v + " р."));
    }
}