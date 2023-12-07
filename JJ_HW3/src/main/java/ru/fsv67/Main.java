package ru.fsv67;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
 * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
 */
public class Main {
    public static void main(String[] args) {
        String path = Employees.class.getName() + "_" + UUID.randomUUID();

        List<Employees> employees = Arrays.asList(
                new Employees(38, "Сергей", "Java разработчик"),
                new Employees(30, "Иван", "Android разработчик")
        );

        SerializableObjectToFile serializableObjectToFile = new SerializableObjectToFile();
        serializableObjectToFile.writingObjectToFile(employees, path);
        serializableObjectToFile.readingObjectFromFile(path);
    }
}